package com.mizuki.employeecrm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizuki.employeecrm.model.Employee;
import com.mizuki.employeecrm.repository.EmployeeRepository;
import com.mizuki.employeecrm.service.EmployeeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
class MockEmployeeControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;
    private static MockHttpServletRequest request;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    EmployeeService serviceMock;

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private Employee employee;

    @BeforeAll
    public static void setup() {
        request = new MockHttpServletRequest();

        request.setParameter("id", "1");
        request.setParameter("firstName", "Mizuki");
        request.setParameter("lastName", "Koike");
        request.setParameter("dob" ,"1991, 9, 6");
        request.setParameter("email" ,"mizuki@samplu.com");
        request.setParameter("salary", "22000");
        request.setParameter("filename", "filename01.jpg");
    }

    @Test
    public void getEmployeeHttpRequest() throws Exception {
        employee.setId(1L);
        employee.setFirstName("Mizuki");
        employee.setLastName("Koike");
        employee.setDob(LocalDate.of(1991, 9, 6));
        employee.setEmail("mizuki@samplu.com");
        employee.setSalary(new BigDecimal("22000"));
        employee.setPhotoFileName("filename01.jpg");


        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$",  hasSize(2)));
    }

}