package com.mizuki.employeecrm.controller;

import com.mizuki.employeecrm.model.Employee;
import com.mizuki.employeecrm.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/form")
@Log4j2
public class FormController {

    private EmployeeService service;

    public FormController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public String showForm() {
        return "form";
    }

    @ModelAttribute("employee")
    public Employee getEmployee() {
        return new Employee();
    }

    @PostMapping
    public String saveEmployee(@Valid Employee employee, Errors errors) {
        log.info(employee);

        if (!errors.hasErrors()) {
            service.saveEmployee(employee);
            return "redirect:employees";
        }
        return "form";

    }

}
