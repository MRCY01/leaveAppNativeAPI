package com.cy.leaveAppNative.reqres;

import java.util.Date;

import com.cy.leaveAppNative.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {
    private String name;
    private String address;
    private String bod;
    private String phone;
    private Employee user;
    private String token;
}
