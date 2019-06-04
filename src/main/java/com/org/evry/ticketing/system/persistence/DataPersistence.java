package com.org.evry.ticketing.system.persistence;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class DataPersistence {
    
    private static final Logger log = Logger.getLogger(DataPersistence.class.getName());
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction userTransaction;

    public DataPersistence() {
        entityManagerFactory = Persistence.createEntityManagerFactory("TicketSystem");
        entityManager = entityManagerFactory.createEntityManager();
        userTransaction = entityManager.getTransaction();
        userTransaction.begin();
    }

    public void startNewTransaction() {
        userTransaction.begin();
    }
    
    public boolean isValidUser(String empId, String pwd) {
        Query query = entityManager.createQuery("SELECT 1 FROM User x where x.userId=:employeeId and x.password=:pwd");
        List<User> users = query.setParameter("employeeId", empId)
                                      .setParameter("pwd", pwd)
                                      .getResultList();
//                                      .getSingleResult();
//        log.info("result: "+ result);
//        return result.equals(1);
        return (users != null && users.size() == 1);
    }
    
    public void createTicket(Ticket ticket) {
        entityManager.persist(ticket);
        committAndClose();
    }
    
    
    public List<Ticket> getTickets(String userId) {
        Query query = entityManager.createQuery("SELECT x FROM Ticket x where x.employeeId=:userId");
        return query.setParameter("userId", userId).getResultList();
    }
    
    public Ticket getTicket(String ticketId) {
        Query query = entityManager.createQuery("SELECT x FROM Ticket x where x.ticketId=:ticketNum");
        return (Ticket) query.setParameter("ticketNum", ticketId).getSingleResult();
    }
    
    /*public List<Employee> getEmployees() {
        Query query = entityManager.createQuery("SELECT x FROM Employee x");
        return query.getResultList();
    }*/
    
    public void committAndClose() {
        userTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public void committ() {
        userTransaction.commit();
    }

    public void rollback() {
        userTransaction.rollback();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
