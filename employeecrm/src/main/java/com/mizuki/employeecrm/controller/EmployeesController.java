package com.mizuki.employeecrm.controller;

import com.mizuki.employeecrm.exception.StorageException;
import com.mizuki.employeecrm.model.Employee;
import com.mizuki.employeecrm.repository.FileStorageRepository;
import com.mizuki.employeecrm.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

import static java.lang.String.format;

@Controller
@RequestMapping("/employees")
@Log4j2
public class EmployeesController {
    public static final String DISPOSITION = """
             attachment; filename="%s"
            """;

    private EmployeeService employeeService;
    private FileStorageRepository fileStorageRepository;


    @Autowired
    public EmployeesController(EmployeeService employeeService, FileStorageRepository fileStorageRepository) {
        this.employeeService = employeeService;
        this.fileStorageRepository = fileStorageRepository;
    }

    //Employee list view

    @GetMapping
    public String showEmployeePage() {
        return "employees";
    }

    @ModelAttribute("employees")
    public Page<Employee> showEmployees(@PageableDefault(size = 5) Pageable page) {
        return employeeService.findAll(page);
    }

    @GetMapping("/images/{resource}")
    public ResponseEntity<Resource> getResource(@PathVariable String resource) {

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, format(DISPOSITION, resource))
                .body(fileStorageRepository.findByName(resource));
    }

    @ModelAttribute
    public Employee getEmployee() {
        return new Employee();
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam(name = "empId") Long id) {
        log.info(id);
        if(id != null) {
            employeeService.deleteEmployee(id);
        }
        return "redirect:/employees";
    }


    // Employee form view

    @GetMapping("/form")
    public String showForm() {
        return "form";
    }

    @PostMapping("/form/save")
    public String saveEmployee(Model model, @Valid Employee employee, Errors errors, @RequestParam("photoFileName") MultipartFile photoFile) throws IOException {
        log.info(employee);
        log.info("Filename " + photoFile.getOriginalFilename());
        log.info("File size " + photoFile.getSize());

        if (!errors.hasErrors()) {
            try {
                employeeService.saveEmployee(employee, photoFile.getInputStream());
                return "redirect:/employees";
            } catch (StorageException e) {
                model.addAttribute("errorMessage", "Server currently not available to save a file");
                return "form";
            }
        }
        return "form";

    }

    @GetMapping("/form/update")
    public String updateEmployee(@RequestParam(name = "empId") Long id, Model model) {

        Employee employee = employeeService.findById(id).get();
        model.addAttribute("employee", employee);

        return "form";
    }

}
