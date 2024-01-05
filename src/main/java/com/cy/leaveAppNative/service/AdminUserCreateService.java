package com.cy.leaveAppNative.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.leaveAppNative.entity.Employee;
import com.cy.leaveAppNative.exeption.ServiceException;
import com.cy.leaveAppNative.repo.EmployeeRepository;
import com.cy.leaveAppNative.reqres.AdminUserCreateRequest;
import com.cy.leaveAppNative.reqres.AdminUserCreateResponse;

import lombok.SneakyThrows;

@Service
public class AdminUserCreateService {

    @Autowired
    EmployeeRepository employeeRepository;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    @SneakyThrows
    public AdminUserCreateResponse userCreate(AdminUserCreateRequest request) {
        AdminUserCreateResponse response = new AdminUserCreateResponse();
        try {
            // Create employee entity
            Employee employee = new Employee();
            employee.setEmpName(request.getEmpName());
            employee.setPassword(request.getPassword());
            employee.setEmail(request.getEmail());
            employee.setBod(request.getBod());
            employee.setPhoneNo(request.getPhone());
            employee.setAddress(request.getAddress());
            employee.setCreatedDate(LocalDateTime.now().format(DATE_TIME_FORMATTER));
            employee.setActive(request.isActive());
            employee.setFirstTimeLogin(true);
            employee.setRoleName(request.getRole());
            employee.setEmploymentStatus(request.getStatus());
            employee.setManagerId(request.getManager());

            employeeRepository.save(employee);
            
            response.setMessage("created successfully");
            response.setStatusCode("200");

            // Employee manager = employeeRepository.findById(request.getManager())
            //     .orElseThrow(()-> new ServiceException("employee not found"));
            // employee.setManager(manager);

        } catch (Exception e) {
            throw e;
        }


        return response;
    }
}
