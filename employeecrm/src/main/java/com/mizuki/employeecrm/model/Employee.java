package com.mizuki.employeecrm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "First name can not be empty")
    private String firstName;

    @NotEmpty(message = "Last name can not be empty")
    private String lastName;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email can not be empty")
    private String email;

    @DecimalMin(value = "1000", message = "Salary must be at least 1000")
    @NotNull(message = "Salary can not be empty")
    private BigDecimal salary;

}
