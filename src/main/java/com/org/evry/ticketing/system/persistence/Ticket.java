package com.org.evry.ticketing.system.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Amit.Sharma, Dec, 2018
 */
@Entity(name = "Ticket")
@Table(name = "Ticket", schema = "ticketingsystem")
public class Ticket  implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String ticketId;
    private String service;
    private String category;
//    private String title;
    private String description;
    private String employeeId;
    private String status;
    private String ticketOwner;
    private Date createdOn;
    private Date modifiedOn;
//    private String modifiedBy;
    
    public Ticket() {}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ticketId == null) ? 0 : ticketId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ticket other = (Ticket) obj;
        if (ticketId == null) {
            if (other.ticketId != null)
                return false;
        } else if (!ticketId.equals(other.ticketId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Ticket [ticketId=" + ticketId + ", service=" + service + ", category=" + category
                + ", employeeId=" + employeeId + ", status=" + status + ", ticketOwner=" + ticketOwner + "]";
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTicketOwner() {
        return ticketOwner;
    }

    public void setTicketOwner(String ticketOwner) {
        this.ticketOwner = ticketOwner;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
    
}
