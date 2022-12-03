package com.mizuki.employeecrm.repository;

import com.mizuki.employeecrm.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    @Query(value = "SELECT photo_file_name FROM employee WHERE id = :id ", nativeQuery = true)
    public String findFilenameByIds(@Param("id") Long id);

}
