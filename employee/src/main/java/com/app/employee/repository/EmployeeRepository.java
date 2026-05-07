package com.app.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.employee.model.Employee;

@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, Integer> {

    default Employee saveEmployee(Employee employee) 
    {
        return save(employee);
    }
    default List<Employee> getEmployees()
    {
        return findAll();
    }
}