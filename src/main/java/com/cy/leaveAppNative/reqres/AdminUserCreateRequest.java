package com.cy.leaveAppNative.reqres;

import com.cy.leaveAppNative.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserCreateRequest {
    private String empName;
    private String password;
    private boolean active;
    private String address;
    private String bod;
    private String phone;
    private String email;
    private Long manager;
    private String role;
    private String status;
    private String token;
    private Employee user;
}
