package com.mizuki.employeecrm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal salary;

}
