package com.mizuki.employeecrm.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeTest {

    @Test
    public void testForEquality() {
        Employee emp1 = new Employee(null, "Mizuki", "Koike", LocalDate.of(1992, 12, 22), "mizuki@samplu.com", new BigDecimal("20000"), "file0");
        Employee emp2 = new Employee(null, "Mizuki", "Koike", LocalDate.of(1992, 12, 22), "mizuki@samplu.com", new BigDecimal("20000"), "file0");

        assertThat(emp1).isEqualTo(emp2);
    }

    @Test
    public void testForInequality() {
        Employee emp1 = new Employee(null, "Mizuki", "Koike", LocalDate.of(1992, 12, 22), "mizuki@samplu.com", new BigDecimal("20000"), "file0");
        Employee emp2 = new Employee(null,"Saiga", "Chichi", LocalDate.of(1998, 12, 25), "saiga@samplu.com", new BigDecimal("14000"), "filename10");

        assertThat(emp1).isNotEqualTo(emp2);
    }
}
