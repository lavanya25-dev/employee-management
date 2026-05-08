package com.app.employee.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.employee.exception.ResourceNotFoundException;
import com.app.employee.exception.UsernameAlreadyExistsException;
import com.app.employee.model.Employee;
import com.app.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    // Register employee
    public Employee register(Employee employee) {

        List<Employee> employees = repository.getEmployees();

        for (Employee emp : employees) {
            if (emp.getUsername().equals(employee.getUsername())) {
                throw new UsernameAlreadyExistsException("Username '" + employee.getUsername() + "' already exists");
            }
        }
        return repository.saveEmployee(employee);
    }

    // Login
    public boolean authenticate(String username, String password) {
        List<Employee> employees = repository.getEmployees();
        return employees.stream().anyMatch(emp -> emp.getUsername().equals(username) && emp.getPassword().equals(password));
    }

    // Second Highest Salary
    public Employee getSecondHighestSalaryEmployee() {
        return repository.getEmployees()
                .stream()
                .sorted(Comparator
                        .comparing(Employee::getExpectedSalary)
                        .reversed())
                .skip(1)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Employee with second highest salary not found. At least 2 employees are required."));
    }
}
