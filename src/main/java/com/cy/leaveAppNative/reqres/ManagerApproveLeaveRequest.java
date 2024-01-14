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
public class ManagerApproveLeaveRequest {
    private Employee user;
    private String token;

    private String leaveRequestId;
}
