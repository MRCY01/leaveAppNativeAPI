package com.cy.leaveAppNative.reqres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerRejectLeaveResponse {
    private String message;
    private String statusCode;
}
