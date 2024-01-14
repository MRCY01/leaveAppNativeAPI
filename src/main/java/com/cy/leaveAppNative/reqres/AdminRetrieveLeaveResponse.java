package com.cy.leaveAppNative.reqres;

import java.util.List;

import com.cy.leaveAppNative.dto.LeaveBalanceDTO;
import com.cy.leaveAppNative.dto.LeaveRequestDTO;
import com.cy.leaveAppNative.entity.LeaveRequested;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminRetrieveLeaveResponse {
    private String message;
    private String statusCode;
    
    private List<LeaveRequestDTO> requestedList;
}
