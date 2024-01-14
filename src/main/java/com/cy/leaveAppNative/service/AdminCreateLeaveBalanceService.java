package com.cy.leaveAppNative.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.leaveAppNative.dto.LeaveBalanceDTO;
import com.cy.leaveAppNative.entity.Employee;
import com.cy.leaveAppNative.entity.LeaveBalance;
import com.cy.leaveAppNative.exeption.ServiceException;
import com.cy.leaveAppNative.jwt.AuthService;
import com.cy.leaveAppNative.repo.EmployeeRepository;
import com.cy.leaveAppNative.repo.LeaveBalanceRepository;
import com.cy.leaveAppNative.reqres.AdminCreateLeaveBalanceResponse;
import com.cy.leaveAppNative.reqres.AdminCreateLeaveBalanceRequest;

import lombok.SneakyThrows;

@Service
public class AdminCreateLeaveBalanceService {
    @Autowired
    AuthService authService;
    @Autowired
    LeaveBalanceRepository leaveBalanceRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    
    @SneakyThrows
    public AdminCreateLeaveBalanceResponse createLeaveBalance(AdminCreateLeaveBalanceRequest request){
        AdminCreateLeaveBalanceResponse response = new AdminCreateLeaveBalanceResponse();
        try {
            if(!authService.checkRole(request.getUser(), "ADMIN")){
                throw new ServiceException("not ADMIN");
            }
            
            LeaveBalance leaveBalance = new LeaveBalance();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
            Employee employee = employeeRepository.findByEmail(request.getEmpEmail());
            leaveBalance.setName(request.getLeaveBalanceName());
            leaveBalance.setLeaveType(request.getLeaveType());
            leaveBalance.setDateAssigned(dtf.format(LocalDateTime.now()));
            leaveBalance.setBalance(request.getBalance());
            leaveBalance.setExpiryDate(request.getExpiryDate());
            leaveBalance.setEmployee(employee);

            leaveBalanceRepository.save(leaveBalance);
            response.setMessage("leave balance created");
            response.setStatusCode("200");
            
        } catch (ServiceException e) {
            throw e;
        }


        return response;
    }
}
