package com.org.evry.ticketing.system.restapi;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Amit.Sharma, Dec, 2018
 */

@XmlRootElement
public class TicketDetail {

    private String employeeId; 
    private String service; 
    private String category; 
    private String description;
    
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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
    @Override
    public String toString() {
        return "TicketDetail [employeeId=" + employeeId + ", service=" + service + ", category=" + category + ", description="
                + description + "]";
    } 
    
}
