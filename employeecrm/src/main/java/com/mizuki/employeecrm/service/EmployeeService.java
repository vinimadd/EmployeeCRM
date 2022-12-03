package com.mizuki.employeecrm.service;

import com.mizuki.employeecrm.model.Employee;
import com.mizuki.employeecrm.repository.EmployeeRepository;
import com.mizuki.employeecrm.repository.FileStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.InputStream;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;
    private final FileStorageRepository fileStorageRepository;


    @Autowired
    public EmployeeService(EmployeeRepository repository, FileStorageRepository fileStorageRepository) {
        this.repository = repository;
        this.fileStorageRepository = fileStorageRepository;
    }

    public Employee saveEmployee(Employee employee, InputStream photoStream) {
        Employee save = repository.save(employee);
        fileStorageRepository.save(employee.getPhotoFileName(), photoStream);

        return save;
    }

    /**
     *
     * @param employee
     * @return method save for testing purposes
     */
    public Employee saveEmployee(Employee employee) {
        Employee save = repository.save(employee);

        return save;
    }


    public Optional<Employee> findById(Long id) {
        Optional<Employee> foundEmployee = repository.findById(id);

        return foundEmployee;
    }

    public Page<Employee> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public Iterable<Employee> findAll() {
        Iterable<Employee> employees = repository.findAll();

        return employees;
    }

    /**
     *
     * @param employee
     * method for testing purposes
     */
    public void updateEmployee(Employee employee) {

        Employee updatedEmployee = new Employee();
        Long id = employee.getId();
        updatedEmployee.setId(id);

        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setDob(employee.getDob());
        updatedEmployee.setEmail(employee.getEmail());
        updatedEmployee.setSalary(employee.getSalary());
        updatedEmployee.setPhotoFileName(employee.getPhotoFileName());

        saveEmployee(updatedEmployee);

    }

    public void deleteEmployee(Long id) {
        String filename = repository.findFilenameByIds(id);
        repository.deleteById(id);
        fileStorageRepository.deleteByName(filename);
    }
}
