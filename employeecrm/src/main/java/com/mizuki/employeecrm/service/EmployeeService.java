package com.mizuki.employeecrm.service;

import com.mizuki.employeecrm.model.Employee;
import com.mizuki.employeecrm.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee saveEmployee(Employee employee) {
        Employee save = repository.save(employee);

        return save;
    }


    public Optional<Employee> findById(Long id) {
        Optional<Employee> foundEmployee = repository.findById(id);

        return foundEmployee;
    }

    public Iterable<Employee> findAll() {
        Iterable<Employee> employees = repository.findAll();

        return employees;
    }

    public void updateEmployee(Employee employee) {

        Employee updatedEmployee = new Employee();
        Long id = employee.getId();
        updatedEmployee.setId(id);

        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setEmail(employee.getEmail());
        updatedEmployee.setSalary(employee.getSalary());
        saveEmployee(updatedEmployee);

    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
