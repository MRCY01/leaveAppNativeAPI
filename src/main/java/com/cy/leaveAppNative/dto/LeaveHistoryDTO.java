package com.cy.leaveAppNative.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveHistoryDTO {
    private String dateApply;
    private String submitDate;
    private String reason;
    private String rejectedReason;
    private String status;
    private String halfday;
}
