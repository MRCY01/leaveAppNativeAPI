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
import com.cy.leaveAppNative.reqres.ManagerRetrieveLeaveRequest;
import com.cy.leaveAppNative.reqres.ManagerRetrieveLeaveResponse;
import com.cy.leaveAppNative.service.ManagerRetrieveLeaveService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.SneakyThrows;

@RestController
public class ManagerRetrieveController {
    @Autowired
    AuthService authService;
    @Autowired
    ManagerRetrieveLeaveService managerRetrieveLeaveService;

    @SneakyThrows
    @PostMapping(path = "/manager/retrieveLeaveList")
    public ManagerRetrieveLeaveResponse retrieveLeave(@Valid @RequestBody ManagerRetrieveLeaveRequest request) {
        Employee user = authService.getUser(request.getToken());
        request.setUser(user);
        return managerRetrieveLeaveService.retrieveLeaveList(request);
    }



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
