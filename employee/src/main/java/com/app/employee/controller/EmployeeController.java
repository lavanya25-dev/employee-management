package com.app.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.employee.model.Employee;
import com.app.employee.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/register")
    public Employee register(@Valid @RequestBody Employee employee) {
        return service.register(employee);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if (service.authenticate(username, password)) {
            return "Login successful";
        } else {
            return "Invalid username or password";
        }
    }

    @GetMapping("/second-highest-salary")
    public Employee getSecondHighestSalary() {
        return service.getSecondHighestSalaryEmployee();
    }
}
