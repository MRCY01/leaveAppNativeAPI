package com.cy.leaveAppNative.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cy.leaveAppNative.exeption.ServiceErrorResponse;
import com.cy.leaveAppNative.exeption.ServiceException;
import com.cy.leaveAppNative.reqres.LoginRequest;
import com.cy.leaveAppNative.reqres.LoginResponse;
import com.cy.leaveAppNative.service.LoginService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.SneakyThrows;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping(path = "/employee/login")
    @SneakyThrows
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return loginService.login(request);
    }

    //EXCEPTION
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
