package com.cy.leaveAppNative.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.leaveAppNative.entity.Employee;
import com.cy.leaveAppNative.exeption.ServiceException;
import com.cy.leaveAppNative.repo.EmployeeRepository;
import com.cy.leaveAppNative.reqres.UpdateProfileReponse;
import com.cy.leaveAppNative.reqres.UpdateProfileRequest;

import lombok.SneakyThrows;

@Service
public class UpdateProfileService {
    @Autowired
    EmployeeRepository employeeRepository;

    @SneakyThrows
    public UpdateProfileReponse updateProfile(UpdateProfileRequest request){
        UpdateProfileReponse response = new UpdateProfileReponse();
        try {
            Employee employee = request.getUser();
            String name = request.getName();
            String address = request.getAddress();
            String bod = request.getBod();
            String phone = request.getPhone();
            // SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

            if(name == null||address==null||bod==null||phone==null){
                throw new ServiceException("incomplete form");
            }
            employee.setEmpName(name);
            employee.setAddress(address);
            employee.setBod(bod);
            employee.setPhoneNo(phone);

            employeeRepository.save(employee);

            response.setMessage("sucessfully update");
            response.setStatusCode("200");
        } catch (ServiceException e) {
            throw e;
        }
        
        return response;
    }
}
