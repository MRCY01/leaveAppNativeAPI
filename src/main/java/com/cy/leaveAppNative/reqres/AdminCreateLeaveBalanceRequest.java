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
public class AdminCreateLeaveBalanceRequest {
    private Employee user;
    private String token;

    private String leaveBalanceName;
    private String leaveType;
    private String empEmail;
    private String balance;
    private String expiryDate;

}
