package com.cy.leaveAppNative.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cy.leaveAppNative.entity.Employee;
import com.cy.leaveAppNative.entity.LeaveRequested;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequested, Long> {
    // List<Employee> findByEmployee(Employee employee);
    List<LeaveRequested> findByEmpId(Employee employee);
    Boolean existsByEmpIdAndApplyDate(Employee employee, String applyDate);
    List<LeaveRequested> findByManagerApproveIsNullAndEmpId_ManagerIdOrderBySubmitDateDesc( Long managerId);
    
}
