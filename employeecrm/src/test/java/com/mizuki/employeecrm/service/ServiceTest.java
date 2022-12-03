package com.mizuki.employeecrm.service;

import com.mizuki.employeecrm.model.Employee;
import com.mizuki.employeecrm.repository.EmployeeRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class ServiceTest {

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeRepository repository;

    @Test
    public void canSaveEmployee() {
        Employee emp1 = new Employee(null, "Mizuki", "Koike", LocalDate.of(1992, 12, 22), "mizuki@samplu.com", new BigDecimal("20000"), "file0.jpg");
        Employee savedEmployee = service.saveEmployee(emp1);

        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void canFindEmployeeById() {
        Employee employee = new Employee(null,"Saiga", "Chichi", LocalDate.of(1990, 3, 21), "saiga@samplu.com", new BigDecimal("14000"), "file1.jpg");
        Employee savedEmployee = service.saveEmployee(employee);
        Employee foundEmployee = service.findById(savedEmployee.getId()).get();

        assertThat(foundEmployee).isEqualTo(savedEmployee);
    }

    @Test
    @Disabled
    public void findAllEmployees() {
        Employee takita = new Employee(null,"Takita", "Uiho", LocalDate.of(1998,3,30), "takita@samplu.com", new BigDecimal("10000"), "file2");
        Employee yokoze = new Employee(null,"Yokoze", "Nobuyo", LocalDate.of(1999, 2, 12), "yokoze@samplu.com", new BigDecimal("12000"), "file3");
        Employee inaba = new Employee(null,"Inaba", "Okiyori", LocalDate.of(1996, 7, 23), "inaba@samplu.com", new BigDecimal("8000"), "file4");
        Employee kishi = new Employee(null,"Kishi", "Mikiyo", LocalDate.of(1994, 6, 8), "kishi@samplu.com", new BigDecimal("9500"), "file5");
        Employee yamazoe = new Employee(null,"Yamazoe", "Tobira", LocalDate.of(1993, 4, 15), "yamazoe@samplu.com", new BigDecimal("7000"), "file6");
        service.saveEmployee(takita);
        service.saveEmployee(yokoze);
        service.saveEmployee(inaba);
        service.saveEmployee(kishi);
        service.saveEmployee(yamazoe);

        Iterable<Employee> employees = service.findAll();
        List<Employee> employeeList = StreamSupport.stream(employees.spliterator(), false).collect(Collectors.toList());

        assertThat(employeeList.size()).isGreaterThanOrEqualTo(5);
    }

    @Test
    public void employeeNotFound() {
        Optional<Employee> foundEmployee = service.findById(-1L);

        assertThat(foundEmployee).isEmpty();
    }

    @Test
    public void canUpdateEmployee() {
        Employee employee = new Employee(null,"Akabori", "Azuki", LocalDate.of(1992, 9, 25), "akabori@samplu.com", new BigDecimal("14000"), "file7");
        Employee savedEmployee = service.saveEmployee(employee);
        Employee foundEmployee = service.findById(savedEmployee.getId()).get();

        savedEmployee.setSalary(new BigDecimal("18000"));
        service.updateEmployee(savedEmployee);
        Employee updatedEmployee = service.findById(savedEmployee.getId()).get();

        assertThat(updatedEmployee.getSalary()).isNotEqualTo(foundEmployee.getSalary());
    }

    @Test
    public void canDeleteEmployee() {
        Employee employee = new Employee(null,"Dobashi", "Aio", LocalDate.of(1997, 4, 29),"dobashi@samplu.com", new BigDecimal("9000"), "filename8");
        Employee savedEmployee = service.saveEmployee(employee);

        Long id = savedEmployee.getId();
        service.deleteEmployee(id);
    }

}
