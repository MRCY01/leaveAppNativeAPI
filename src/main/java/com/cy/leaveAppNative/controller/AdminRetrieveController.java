package com.cy.leaveAppNative.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cy.leaveAppNative.entity.Employee;
import com.cy.leaveAppNative.exeption.ServiceErrorResponse;
import com.cy.leaveAppNative.exeption.ServiceException;
import com.cy.leaveAppNative.jwt.AuthService;
import com.cy.leaveAppNative.reqres.AdminRetrieveLeaveBalanceRequest;
import com.cy.leaveAppNative.reqres.AdminRetrieveLeaveBalanceResponse;
import com.cy.leaveAppNative.reqres.AdminRetrieveLeaveRequest;
import com.cy.leaveAppNative.reqres.AdminRetrieveLeaveResponse;
import com.cy.leaveAppNative.reqres.AdminRetrieveProfileRequest;
import com.cy.leaveAppNative.reqres.AdminRetrieveProfileResponse;
import com.cy.leaveAppNative.service.AdminRetrieveLeaveBalanceService;
import com.cy.leaveAppNative.service.AdminRetrieveLeaveService;
import com.cy.leaveAppNative.service.AdminRetrieveProfileService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
public class AdminRetrieveController {

    @Autowired
    AuthService authService;
    @Autowired
    AdminRetrieveProfileService adminRetrieveProfileService;
    @Autowired
    AdminRetrieveLeaveBalanceService adminRetrieveLeaveBalanceService;
    @Autowired
    AdminRetrieveLeaveService adminRetrieveLeaveService;

    @PostMapping(path = "/admin/allUser/retrieveProfile")
    public AdminRetrieveProfileResponse showAllEmp(@Valid @RequestBody AdminRetrieveProfileRequest request) {
        Employee user = authService.getUser(request.getToken());
        request.setUser(user);
        return adminRetrieveProfileService.showAllEmployee(request);
    }

    @PostMapping(path = "/admin/allUser/retrieveLeaveBalance")
    public AdminRetrieveLeaveBalanceResponse retrieveLeaveBalance(@RequestBody AdminRetrieveLeaveBalanceRequest request) {
        Employee user = authService.getUser(request.getToken());
        request.setUser(user);
        return adminRetrieveLeaveBalanceService.retrieveLeaveBalance(request);
    }

    @PostMapping(path = "/admin/retrieveLeaveList")
    public AdminRetrieveLeaveResponse retrieveLeaveBalance(@RequestBody AdminRetrieveLeaveRequest request) {
        Employee user = authService.getUser(request.getToken());
        request.setUser(user);
        return adminRetrieveLeaveService.retrieveLeaveList(request);
    }
    
    // EXCEPTION
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServiceException.class)
    public ServiceErrorResponse handleServiceException(ServiceException e) {
        e.printStackTrace();
        ServiceErrorResponse response = new ServiceErrorResponse();
        response.setMessage(e.getMessage());
        response.setStatusCode("400");
        return response;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ServiceErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
        ServiceErrorResponse response = new ServiceErrorResponse();
        response.setMessage(ex.getMessage());
        response.setStatusCode("404");
        return response;
    }
}
