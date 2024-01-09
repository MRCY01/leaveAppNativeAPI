package com.cy.leaveAppNative.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.leaveAppNative.exeption.ServiceException;

import lombok.SneakyThrows;

@Service
public class JWTService {
    @Autowired
    JWTUtil jwtUtil;

    @SneakyThrows
    public String generateToken(String userId, String email){
        String token ="";
        try {
            token = jwtUtil.generateToken(userId, email);
            if(token.equals("")){
                throw new ServiceException("tokon not exist");
            }
            
        } catch (ServiceException e) {
            throw e;
        }
        return token;
    }

    @SneakyThrows
    public String getUserID(String token){
        String userId="";
        try{
            userId = jwtUtil.extractUserId(token);
            if(userId == ""){
                throw new ServiceException("failed to extract userId");
            }

        }catch(ServiceException e){
            throw e;
        }
        return userId;
    }

    @SneakyThrows
    public String getUserEmail(String token){
        String email ="";
        try {
            email = jwtUtil.extractUserEmail(token);
            if(email == ""){
                throw new ServiceException("failed to extract email");
            }
            
        } catch (ServiceException e) {
            throw e;
        }

        return email;
    }
    
    @SneakyThrows
    public boolean validateToken(String token, String userID){
        String extractedUserId = ""; 
        boolean isTokenExpired;
        try {
            extractedUserId = jwtUtil.extractUserId(token);
            isTokenExpired = jwtUtil.isTokenExpired(token);
            if(isTokenExpired){
                throw new ServiceException("Token Expired");
            }
            if(userID.equalsIgnoreCase(extractedUserId)){
                throw new ServiceException("userId not match");
            }
             
        } catch (ServiceException e) {
            throw e;
        }
        return true; 
    }
    

}

