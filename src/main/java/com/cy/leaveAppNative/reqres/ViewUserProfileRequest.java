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
public class ViewUserProfileRequest {
    private String token;
    private Employee user;
}
