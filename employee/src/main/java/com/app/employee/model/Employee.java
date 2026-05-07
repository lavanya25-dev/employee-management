package com.app.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee 
{
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String username;
    private String password;
    private double expectedSalary;

    public Employee() {}

     public Employee(Integer id, String name, String username, String password, double expectedSalary) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.expectedSalary = expectedSalary;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getUsername() 
    {   
        return username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getPassword() 
    {   
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public double getExpectedSalary() 
    {
        return expectedSalary;
    }

    public void setExpectedSalary(double expectedSalary) 
    {
        this.expectedSalary = expectedSalary;
    }
}
