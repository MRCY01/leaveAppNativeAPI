package com.cy.leaveAppNative.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.leaveAppNative.entity.Employee;
import com.cy.leaveAppNative.exeption.ServiceException;
import com.cy.leaveAppNative.jwt.JWTService;
import com.cy.leaveAppNative.repo.EmployeeRepository;
import com.cy.leaveAppNative.reqres.LoginRequest;
import com.cy.leaveAppNative.reqres.LoginResponse;

import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@Service
public class LoginService {
    @Autowired
    JWTService jwtService;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    HttpServletResponse httpServletResponse;

    @SneakyThrows
    public LoginResponse login(LoginRequest loginRequest) {
        try{
            LoginResponse response = new LoginResponse();
            String loginEmail = loginRequest.getLoginEmail();
            String password = loginRequest.getPassword();

            if(loginEmail.equals("") || loginEmail == null || password.equals("") || password == null) {
                // System.out.println("credential not complete");
                throw new ServiceException("credential not complete");
            }

            Employee employee = employeeRepository.findByEmail(loginEmail);
            if (employee == null || (!employee.getPassword().equalsIgnoreCase(password))) {
                throw new ServiceException("incorrect credential");
            }

            String token = jwtService.generateToken(employee.getId().toString(), employee.getEmail());
            String roleName = employee.getRoleName();
            String message = "successful";
            String statusCode = "200";

            response.setMessage(message);
            response.setToken(token);
            response.setRole(roleName);
            response.setStatusCode(statusCode);

            return response;

        }catch (ServiceException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
    }
}

