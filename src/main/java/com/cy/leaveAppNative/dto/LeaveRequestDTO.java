package com.cy.leaveAppNative.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestDTO {
    private Long empId;
    private Long id;
    private String leaveBalance;
    private Boolean managerApprove;
    private String applyDate;
    private String halfDay;
    private String reason;
    private String submitDate;
}
