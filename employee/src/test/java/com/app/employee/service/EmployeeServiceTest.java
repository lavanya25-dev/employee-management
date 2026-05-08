package com.app.employee.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.employee.exception.ResourceNotFoundException;
import com.app.employee.exception.UsernameAlreadyExistsException;
import com.app.employee.model.Employee;
import com.app.employee.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    @Test
    void testSecondHighestSalaryEmployee() {

        Employee e1 = new Employee(1, "Ram", "ram", "123", 30000.0);
        Employee e2 = new Employee(2, "Sam", "sam", "123", 50000.0);
        Employee e3 = new Employee(3, "John", "john", "123", 40000.0);

        List<Employee> employees = Arrays.asList(e1, e2, e3);

        when(repository.getEmployees()).thenReturn(employees);

        Employee result = service.getSecondHighestSalaryEmployee();

        assertEquals("John", result.getName());
    }

    @Test
    void testSecondHighestSalaryEmployeeException() {

        Employee e1 = new Employee(1, "Ram", "ram", "123", 30000.0);

        when(repository.getEmployees()).thenReturn(Arrays.asList(e1));

        assertThrows(ResourceNotFoundException.class, () -> {
            service.getSecondHighestSalaryEmployee();
        });
    }

    @Test
    void testAuthenticateSuccess() {

        Employee e1 = new Employee(1, "Ram", "ram", "123", 30000.0);

        when(repository.getEmployees()).thenReturn(Arrays.asList(e1));

        boolean result = service.authenticate("ram", "123");

        assertEquals(true, result);
    }

    @Test
    void testAuthenticateFailure() {

        Employee e1 = new Employee(1, "Ram", "ram", "123", 30000.0);

        when(repository.getEmployees()).thenReturn(Arrays.asList(e1));

        boolean result = service.authenticate("ram", "wrong");

        assertEquals(false, result);
    }

    @Test
    void testSecondHighestSalaryException() {

        List<Employee> employees = new ArrayList<>();

        Employee emp
                = new Employee(1, "John", "john", "123", 50000.0);

        employees.add(emp);

        when(repository.getEmployees()).thenReturn(employees);

        assertThrows(ResourceNotFoundException.class, () -> {
            service.getSecondHighestSalaryEmployee();
        });
    }

    @Test
    void testDuplicateUsername() {

        Employee existing
                = new Employee(1, "John", "john", "123", 50000.0);

        Employee newEmployee
                = new Employee(2, "John2", "john", "456", 60000.0);

        List<Employee> employees = new ArrayList<>();
        employees.add(existing);

        when(repository.getEmployees()).thenReturn(employees);

        assertThrows(UsernameAlreadyExistsException.class, () -> {
            service.register(newEmployee);
        });
    }
}
