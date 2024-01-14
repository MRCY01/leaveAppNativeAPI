package com.cy.leaveAppNative.reqres;

import java.util.List;

import com.cy.leaveAppNative.dto.LeaveApplyDTO;
import com.cy.leaveAppNative.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyLeaveRequest {
    private Employee user;
    private String token;

    private String leaveBalance;
    private String totalApplyDays;
    private List<LeaveApplyDTO> leaveApplyDTOList;
}
