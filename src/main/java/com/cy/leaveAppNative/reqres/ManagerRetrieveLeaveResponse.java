package com.cy.leaveAppNative.reqres;

import java.util.List;

import com.cy.leaveAppNative.dto.LeaveRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerRetrieveLeaveResponse {
    private String message;
    private String statusCode;
    
    private List<LeaveRequestDTO> requestedList;
}
