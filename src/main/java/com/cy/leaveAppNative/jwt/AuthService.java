package com.cy.leaveAppNative.jwt;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.leaveAppNative.entity.Employee;
import com.cy.leaveAppNative.repo.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private JWTUtil jwtUtil;

    @SneakyThrows
    public String getToken(String email, String password) {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee.getEmail().isEmpty()) {
            throw new Exception("Invalid email");
        }
        if(!employee.getPassword().equalsIgnoreCase(password)){
            throw new Exception("incorrect password");   
        }

        // Generate a token to store user
        String token = jwtUtil.generateToken(employee.getId().toString(), employee.getEmail());
        return token;
    }

    @SneakyThrows
    public String getUserRole(String token) {
        // Extract the user ID from the token
        String userId = jwtUtil.extractUserId(token);

        // String roleName = "";
        // List<String> roleList = new ArrayList<>();
        Employee employee = employeeRepository.findById(Long.parseLong(userId))
                .orElseThrow(()->new EntityNotFoundException());
        if (employee == null) {
            throw new Exception("User not found");
        }

        String roleName = employee.getRoleName();
        // List<EmployeeRole> empRoles = employee.getEmpRole();
        // for (EmployeeRole empRole : empRoles) {
        //     Role role = empRole.getRole();
        //     roleList.add(role.getRoleName());
        // }
        // return roleList;
        return roleName;
    }
    @SneakyThrows
    public Employee getUser(String token) {
        // Extract the user ID from the token
        String userId = jwtUtil.extractUserId(token);

        Employee employee = employeeRepository.findById(Long.parseLong(userId))
                .orElseThrow(()->new EntityNotFoundException());


        return employee;
    }

    public boolean isCorrectRole(String role,String token){
        // Employee user = getUser(token);
        String roleName = getUserRole(token);
        jwtUtil.validateToken(token, jwtUtil.extractUserId(token));
    
        return !roleName.equals(role);
    }

    public boolean hasRole(Employee employee, String role){
        if(employee==null){
            return false;
        }
        // List<EmployeeRole> employeeRoleList = employee.getEmpRole();

        // List<String> roleList = employeeRoleList.stream()
        //         .map(EmployeeRole::getRole)
        //         .map(Role::getRoleName)
        //         .collect(Collectors.toList());

        String roleName = employee.getRoleName();

        return roleName.equals(role);
    }
}

