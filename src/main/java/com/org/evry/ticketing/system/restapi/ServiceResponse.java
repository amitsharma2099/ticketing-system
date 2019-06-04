package com.org.evry.ticketing.system.restapi;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author Amit.Sharma, Dec, 2018
 */
@XmlRootElement
public class ServiceResponse {

    private String status;
    private String ticketId;
//    private String message;
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getTicketId() {
        return ticketId;
    }
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
    /*public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }*/
    @Override
    public String toString() {
        return "ServiceResponse [status=" + status + ", ticketId=" + ticketId + "]";
    }
    
}
