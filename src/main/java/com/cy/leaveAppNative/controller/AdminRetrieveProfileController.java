package com.cy.leaveAppNative.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cy.leaveAppNative.entity.Employee;
import com.cy.leaveAppNative.jwt.AuthService;
import com.cy.leaveAppNative.reqres.AdminRetrieveProfileRequest;
import com.cy.leaveAppNative.reqres.AdminRetrieveProfileResponse;
import com.cy.leaveAppNative.service.AdminRetrieveProfileService;

import jakarta.validation.Valid;

@RestController
public class AdminRetrieveProfileController {

    @Autowired
    AuthService authService;
    @Autowired
    AdminRetrieveProfileService adminRetrieveProfileService;

    @PostMapping(path = "/admin/user/retrieveAll")
    public AdminRetrieveProfileResponse showAllEmp(@Valid @RequestBody AdminRetrieveProfileRequest request) {
        Employee user = authService.getUser(request.getToken());
        request.setUser(user);
        return adminRetrieveProfileService.showAllEmployee(request);
    }
}
