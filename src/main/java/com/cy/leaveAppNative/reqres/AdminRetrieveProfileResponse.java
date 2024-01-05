package com.cy.leaveAppNative.reqres;

import java.util.List;

import com.cy.leaveAppNative.dto.EmployeeDetailsDTO;
import com.cy.leaveAppNative.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminRetrieveProfileResponse {
    private String message;
    private String statusCode;
    private List<EmployeeDetailsDTO> employeeList;
}
