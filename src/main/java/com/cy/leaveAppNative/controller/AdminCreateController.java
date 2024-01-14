package com.cy.leaveAppNative.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.cy.leaveAppNative.entity.Employee;
import com.cy.leaveAppNative.exeption.ServiceErrorResponse;
import com.cy.leaveAppNative.exeption.ServiceException;
import com.cy.leaveAppNative.jwt.AuthService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.SneakyThrows;

import com.cy.leaveAppNative.reqres.AdminCreateLeaveBalanceResponse;
import com.cy.leaveAppNative.reqres.AdminCreateLeaveBalanceRequest;
import com.cy.leaveAppNative.reqres.AdminUserCreateRequest;
import com.cy.leaveAppNative.reqres.AdminUserCreateResponse;
import com.cy.leaveAppNative.service.AdminCreateLeaveBalanceService;
import com.cy.leaveAppNative.service.AdminUserCreateService;

@RestController
public class AdminCreateController {
    @Autowired
    AuthService authService;
    @Autowired
    AdminUserCreateService adminUserCreateService;
    @Autowired
    AdminCreateLeaveBalanceService adminCreateLeaveBalanceService;

    @PostMapping(path = "/admin/user/create")
    @SneakyThrows
    public AdminUserCreateResponse updateProfile(@Valid  @RequestBody AdminUserCreateRequest request) {
        Employee user = authService.getUser(request.getToken());
        request.setUser(user);
        return adminUserCreateService.userCreate(request);
    }

    @PostMapping(path = "/admin/leaveBalance/create")
    @SneakyThrows
    public AdminCreateLeaveBalanceResponse createLeaveBalance (@Valid @RequestBody AdminCreateLeaveBalanceRequest request){
        Employee user = authService.getUser(request.getToken());
        request.setUser(user);
        return adminCreateLeaveBalanceService.createLeaveBalance(request);
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
