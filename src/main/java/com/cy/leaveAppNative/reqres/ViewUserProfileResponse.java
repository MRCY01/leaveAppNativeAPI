package com.cy.leaveAppNative.reqres;

import com.cy.leaveAppNative.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewUserProfileResponse {
    private String address;
    private String bod;
    private String email;
    private String name;
    private String status;
    private String maritualStatus;
    private String phone;
    private String role;
    // private Long managerId;
    private String manager;
}
