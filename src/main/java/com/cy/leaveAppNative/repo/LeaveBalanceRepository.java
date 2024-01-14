package com.cy.leaveAppNative.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cy.leaveAppNative.entity.Employee;
import com.cy.leaveAppNative.entity.LeaveBalance;
import java.util.List;


@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {
    // List<Employee> findByEmployee(Employee employee);
    // LeaveBalance findByEmployee(Employee employee);
    // LeaveBalance findByName(String name);
    LeaveBalance findByEmployeeAndName(Employee employee, String name);
}
