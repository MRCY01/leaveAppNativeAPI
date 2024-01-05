package com.cy.leaveAppNative.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailsDTO {
    private String employeeId;
    private String employeeName;
    private String email;
    private String createdDate;
    private String approvedDate;
    private boolean active;
    private String address;
    private String bod;
    private String maritalStatus;
    private String phone;
    private String role;
}
