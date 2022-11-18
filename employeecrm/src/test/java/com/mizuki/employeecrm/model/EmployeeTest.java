package com.mizuki.employeecrm.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeTest {

    @Test
    public void testForEquality() {
        Employee emp1 = new Employee(null,"Mizuki", "Koike", "mizuki@samplu.com", new BigDecimal("17000"));
        Employee emp2 = new Employee(null,"Mizuki", "Koike", "mizuki@samplu.com", new BigDecimal("17000"));

        assertThat(emp1).isEqualTo(emp2);
    }

    @Test
    public void testForInequality() {
        Employee emp1 = new Employee(null,"Mizuki", "Koike", "mizuki@samplu.com", new BigDecimal("17000"));
        Employee emp2 = new Employee(null,"Saiga", "Chichi", "saiga@samplu.com", new BigDecimal("14000"));

        assertThat(emp1).isNotEqualTo(emp2);
    }
}
