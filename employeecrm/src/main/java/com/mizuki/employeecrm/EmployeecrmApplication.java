package com.mizuki.employeecrm;

import com.mizuki.employeecrm.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class EmployeecrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeecrmApplication.class, args);
	}

	@Bean
	@Scope(value = "prototype")
	Employee getEmployee () {
		return new Employee();
	}

}
