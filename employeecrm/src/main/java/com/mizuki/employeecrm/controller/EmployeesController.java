package com.mizuki.employeecrm.controller;

import com.mizuki.employeecrm.model.Employee;
import com.mizuki.employeecrm.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
@Log4j2
public class EmployeesController {

    private EmployeeService employeeService;

    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String showEmployeePage() {
        return "employees";
    }

    @ModelAttribute("employees")
    public Page<Employee> showEmployees(@PageableDefault(size = 7) Pageable page) {
        return employeeService.findAll(page);
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
}
