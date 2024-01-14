package com.cy.leaveAppNative.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveBalanceDTO {
    private Long leaveBalanceId;
    private String leaveName;
    private String balance;
    private String leaveType;
    private String expiryDate;
    private String empId;
    private String empName;
    private String empEmail;
    private String totalLeave;

}
