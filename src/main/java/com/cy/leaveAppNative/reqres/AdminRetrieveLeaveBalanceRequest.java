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
public class AdminRetrieveLeaveBalanceRequest {
    private String token;
    private Employee user;
}
