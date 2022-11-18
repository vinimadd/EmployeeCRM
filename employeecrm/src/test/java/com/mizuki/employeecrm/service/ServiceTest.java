package com.mizuki.employeecrm.service;

import com.mizuki.employeecrm.model.Employee;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private EmployeeService service;


    @Test
    public void canSaveEmployee() {
        Employee emp1 = new Employee(null,"Mizuki", "Koike", "mizuki@samplu.com", new BigDecimal("17000"));
        Employee savedEmployee = service.saveEmployee(emp1);

        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    @Test
    public void canFindEmployeeById() {
        Employee employee = new Employee(null,"Saiga", "Chichi", "saiga@samplu.com", new BigDecimal("14000"));
        Employee savedEmployee = service.saveEmployee(employee);
        Employee foundEmployee = service.findById(savedEmployee.getId()).get();

        assertThat(foundEmployee).isEqualTo(savedEmployee);
    }

    @Test
    @Disabled
    public void findAllEmployees() {
        Employee takita = new Employee(null,"Takita", "Uiho", "takita@samplu.com", new BigDecimal("10000"));
        Employee yokoze = new Employee(null,"Yokoze", "Nobuyo", "yokoze@samplu.com", new BigDecimal("12000"));
        Employee inaba = new Employee(null,"Inaba", "Okiyori", "inaba@samplu.com", new BigDecimal("8000"));
        Employee kishi = new Employee(null,"Kishi", "Mikiyo", "kishi@samplu.com", new BigDecimal("9500"));
        Employee yamazoe = new Employee(null,"Yamazoe", "Tobira", "yamazoe@samplu.com", new BigDecimal("7000"));
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
        Employee employee = new Employee(null,"Akabori", "Azuki", "akabori@samplu.com", new BigDecimal("14000"));
        Employee savedEmployee = service.saveEmployee(employee);
        Employee foundEmployee = service.findById(savedEmployee.getId()).get();

        savedEmployee.setSalary(new BigDecimal("18000"));
        service.updateEmployee(savedEmployee);
        Employee updatedEmployee = service.findById(savedEmployee.getId()).get();

        assertThat(updatedEmployee.getSalary()).isNotEqualTo(foundEmployee.getSalary());
    }

    @Test
    public void canDeleteEmployee() {
        Employee employee = new Employee(null,"Dobashi", "Aio", "dobashi@samplu.com", new BigDecimal("9000"));
        Employee savedEmployee = service.saveEmployee(employee);

        Long id = savedEmployee.getId();
        service.deleteEmployee(id);
    }


}
