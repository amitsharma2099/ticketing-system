package com.org.evry.ticketing.system.restapi;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.org.evry.ticketing.system.persistence.DataPersistence;
import com.org.evry.ticketing.system.persistence.Ticket;

/**
 * @author Amit.Sharma, Dec, 2018
 */

@Singleton
@Path("/tickets")
public class TicketingSystemService {

    private static final Logger log = Logger.getLogger(TicketingSystemService.class.getName());

    @GET
    @Path("/validateUser")
    public Response isValidUser(@Context HttpServletRequest req) {
        log.info("START: isValidUser");
        String authHeader = req.getHeader("Authorization");
        log.info("Auth Header: "+ authHeader);
        
        if(authHeader == null || authHeader.isEmpty()) {
            return Response.status(Status.UNAUTHORIZED).entity("Please provide credentials to access this service.").build();
        }
        
        String[] authParts = authHeader.split("\\s+");
        String authInfo = authParts[1];
        
        byte[] bytes = null;
        bytes = Base64.getDecoder().decode(authInfo);
        String decodedAuth = new String(bytes);
        log.info(decodedAuth);
        
        String user = decodedAuth.split(":")[0];
        String pwd = decodedAuth.split(":")[1];
        log.info("User: "+ user);
//        log.info("Pwd: "+ pwd);
        
        if(user == null || user.isEmpty() || pwd == null || pwd.isEmpty()) {
            return Response.status(Status.UNAUTHORIZED).entity("Please provide correct credentials to access this service.").build();
        }
        
        String encodedPwd = Base64.getEncoder().encodeToString(pwd.getBytes());
//        log.info("encodedPwd: "+encodedPwd);
        
        DataPersistence persistence = new DataPersistence();
        boolean isValidUser = persistence.isValidUser(user, encodedPwd);
        log.info("isValidUser: "+isValidUser);
        
        if (isValidUser) {
            return Response.ok().entity("Valid user").build();
        } else {
            return Response.status(Status.UNAUTHORIZED).entity("Invalid user").build();
        }
    }
    

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTicket(TicketDetail ticket, @Context HttpServletRequest req) {
        Response response = isValidUser(req);
        
        if(response.getStatus() != Status.OK.getStatusCode()) {
            return response;
        }
        log.info("START: createTicket");
        log.info("ticket: "+ ticket);
        
        if(ticket == null) {
            return Response.status(Status.BAD_REQUEST).entity("Please provide the ticket detail.").build();
        }
        
        Ticket newTicket = new Ticket();
        newTicket.setCategory(ticket.getCategory());
        newTicket.setCreatedOn(new Date());
        newTicket.setDescription(ticket.getDescription());
        newTicket.setEmployeeId(ticket.getEmployeeId());
        newTicket.setService(ticket.getService());
        newTicket.setStatus("OPEN");
        
        String ticketId = UUID.randomUUID().toString().replace("-", "");
        newTicket.setTicketId(ticketId);
        
        DataPersistence persistence = new DataPersistence();
        persistence.createTicket(newTicket);
        
        ServiceResponse resp = new ServiceResponse();
        resp.setStatus(Status.OK.name());
        resp.setTicketId(ticketId);

        return Response.ok().entity(resp).build();
    }
    

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{userId}")
//    public List<Ticket> getAllTicketsOfUser(@PathParam("userId") String userId, @Context HttpServletRequest req) {
    public Response getAllTicketsOfUser(@PathParam("userId") String userId, @Context HttpServletRequest req) {
        Response response = isValidUser(req);
        
        if(response.getStatus() != Status.OK.getStatusCode()) {
            return response;
        }
        
        if(userId == null || userId.isEmpty()) {
            return Response.status(Status.BAD_REQUEST).entity("Please provide the user Id to get tickets detail...!").build();
        }
        
        log.info("START: getAllTickets");
        List<Ticket> tikcets = Collections.EMPTY_LIST;
        
        DataPersistence persistence = new DataPersistence();
        tikcets = persistence.getTickets(userId);
        log.info("Total tikcets: " + tikcets.size());

        if(tikcets.size() <= 0) {
            return Response.status(Status.NOT_FOUND).entity("No ticket found...!").build();
        }
        
        GenericEntity<List<Ticket>> entity = new GenericEntity<List<Ticket>>(tikcets){};
        response = Response.ok(entity).build(); 
        
//        return tikcets;
        return response;
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{ticketId}")
    public Response getTicketDetail(@PathParam("ticketId") String ticketId, @Context HttpServletRequest req) {
        Response response = isValidUser(req);
        
        if(response.getStatus() != Status.OK.getStatusCode()) {
            return response;
        }
        
        log.info("START: getTicketDetail");
        log.info("Searching for ticket: "+ ticketId);
        
        if(ticketId != null && !ticketId.isEmpty()) {
            DataPersistence persistence = new DataPersistence();
            Ticket tikcet = persistence.getTicket(ticketId);
            log.info("Tikcet detail: "+ tikcet);
            response = Response.ok().entity(tikcet).build();
        }else {
            response = Response.status(Status.NOT_FOUND).entity("Ticket not found...!").build();
        }
        return response; 
    }

    /*@PUT
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTicket(FormDataMultiPart multiPart, @Context HttpServletRequest req) {
        Response response = isValidUser(req);
        
        if(response.getStatus() != Status.OK.getStatusCode()) {
            return response;
        }
        log.info("START: updateTicket");
        String response = "{\r\n" + "    \"ticketId\": \"2222\",\r\n" + "    \"status\": \"success\"\r\n" + "}";

        return Response.ok().entity(response).build();
    }*/
    
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/test")
    public Response testService() {
        log.info("Service is up and running...");
        return Response.ok().entity("Service is up and running...").build();
    }

}
