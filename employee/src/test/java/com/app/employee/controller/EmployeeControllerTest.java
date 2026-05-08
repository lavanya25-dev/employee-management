package com.app.employee.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import com.app.employee.model.Employee;
import com.app.employee.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    @Test
    void testLoginSuccess() throws Exception {

        when(service.authenticate("ram", "123"))
                .thenReturn(true);

        mockMvc.perform(post("/login")
                .param("username", "ram")
                .param("password", "123"))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"));
    }

    @Test
    void testLoginFailure() throws Exception {

        when(service.authenticate("ram", "wrong"))
                .thenReturn(false);

        mockMvc.perform(post("/login")
                .param("username", "ram")
                .param("password", "wrong"))
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid username or password"));
    }

    @Test
    void testGetSecondHighestSalary() throws Exception {

        Employee employee
                = new Employee(1, "John", "john", "123", 40000.0);

        when(service.getSecondHighestSalaryEmployee())
                .thenReturn(employee);

        mockMvc.perform(get("/second-highest-salary"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }
}
