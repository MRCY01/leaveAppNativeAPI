package com.cy.leaveAppNative.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cy.leaveAppNative.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // List<Employee> findByEmployee(Employee employee);
    Employee findByEmail(String email);
}
