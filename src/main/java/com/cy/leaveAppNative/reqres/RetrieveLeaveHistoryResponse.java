package com.cy.leaveAppNative.reqres;



import java.util.List;

import com.cy.leaveAppNative.dto.LeaveHistoryDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveLeaveHistoryResponse {
    private String message;
    private String statusCode;

    private List<LeaveHistoryDTO> leaveHistoryDTO;
    
}
