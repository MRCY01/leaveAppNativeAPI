package com.cy.leaveAppNative.exeption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceException extends Exception{

    private  Object extraInfo;
    private String message;
    public ServiceException(String message) {
        this.message = message;
    }
}
