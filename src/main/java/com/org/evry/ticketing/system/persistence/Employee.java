package com.org.evry.ticketing.system.persistence;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Amit.Sharma, Dec, 2018
 */
@Entity(name = "Employee")
@Table(name = "Employee", schema = "ticketingsystem")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String employeeId;
    private String employeeName;
    private String email;
    private String projectName;
    private String managerEmpId;
    private String location;
    private String title;
    private String contact;
//    private Date dateOfJoining;
    
    public Employee() {}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
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
        Employee other = (Employee) obj;
        if (employeeId == null) {
            if (other.employeeId != null)
                return false;
        } else if (!employeeId.equals(other.employeeId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", email=" + email
                + ", projectName=" + projectName + ", managerEmpId=" + managerEmpId + ", location=" + location + "]";
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getManagerEmpId() {
        return managerEmpId;
    }

    public void setManagerEmpId(String managerEmpId) {
        this.managerEmpId = managerEmpId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContact() {
        return contact;
    }
}
