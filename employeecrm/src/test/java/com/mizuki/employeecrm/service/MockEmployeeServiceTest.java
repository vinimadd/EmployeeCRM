package com.mizuki.employeecrm.service;

import com.mizuki.employeecrm.model.Employee;
import com.mizuki.employeecrm.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class MockEmployeeServiceTest {


    @Mock
    private EmployeeService employeeServiceMock;


    @Test
    void saveEmployeeUsingMock() {
    }

    @Test
    void findByIdUsingMock() {
    }

    @Test
    void updateEmployeeUsingMock() {
    }

    @Test
    void deleteEmployeeUsingMock() {
    }

}