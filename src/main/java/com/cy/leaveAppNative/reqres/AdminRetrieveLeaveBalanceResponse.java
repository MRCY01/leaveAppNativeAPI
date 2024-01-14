package com.cy.leaveAppNative.reqres;

import java.util.List;

import com.cy.leaveAppNative.dto.LeaveBalanceDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminRetrieveLeaveBalanceResponse {
    private List<LeaveBalanceDTO> leaveBalanceList;
    private String totalLeave;
    private String message;
    private String statusCode;
}
