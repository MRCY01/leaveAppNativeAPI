package com.cy.leaveAppNative.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.leaveAppNative.entity.Employee;
import com.cy.leaveAppNative.repo.EmployeeRepository;
import com.cy.leaveAppNative.reqres.ViewUserProfileRequest;
import com.cy.leaveAppNative.reqres.ViewUserProfileResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;

@Service
public class ViewProfileService {
    @Autowired
    EmployeeRepository employeeRepository;

    @SneakyThrows
    public ViewUserProfileResponse viewUserProfile(ViewUserProfileRequest request) {

        ViewUserProfileResponse response = new ViewUserProfileResponse();
        Long empId = request.getUser().getId();
        Employee user = employeeRepository.findById(empId)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));
        response.setName(user.getEmpName());
        response.setEmail(user.getEmail());
        response.setBod(user.getBod());
        response.setStatus(user.getEmploymentStatus());
        response.setPhone(user.getPhoneNo());
        response.setAddress(user.getAddress());
        response.setRole(user.getRoleName());
        // response.setManagerId(user.getManagerId());
        Long managerId = user.getManagerId();
        Employee manager = employeeRepository.findById(managerId)
            .orElseThrow(() -> new EntityNotFoundException("manager assigned not found"));
        response.setManager(manager.getEmpName());
        return response;
    }
}
