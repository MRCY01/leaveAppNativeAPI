package com.cy.leaveAppNative.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.leaveAppNative.dto.EmployeeDetailsDTO;
import com.cy.leaveAppNative.entity.Employee;
import com.cy.leaveAppNative.exeption.ServiceException;
import com.cy.leaveAppNative.jwt.AuthService;
import com.cy.leaveAppNative.repo.EmployeeRepository;
import com.cy.leaveAppNative.reqres.AdminRetrieveProfileRequest;
import com.cy.leaveAppNative.reqres.AdminRetrieveProfileResponse;

import lombok.SneakyThrows;

@Service
public class AdminRetrieveProfileService {
    @Autowired
    AuthService authService;
    @Autowired
    EmployeeRepository employeeRepository;
    
    @SneakyThrows
    public AdminRetrieveProfileResponse showAllEmployee(AdminRetrieveProfileRequest request){
        AdminRetrieveProfileResponse response = new AdminRetrieveProfileResponse();

        try{
            Employee user = request.getUser();
            if(!authService.hasRole(user,"ADMIN")){
                throw new ServiceException("user is not admin");
            }
            if(employeeRepository.findAll().isEmpty()){
                throw  new ServiceException("no employee record found");
            }

            List<Employee> employeeList = employeeRepository.findAll();
            List<EmployeeDetailsDTO> detailList = new ArrayList<>();
            for (Employee emp: employeeList){
                EmployeeDetailsDTO details = new EmployeeDetailsDTO();
                details.setEmployeeId(emp.getId().toString());
                details.setEmployeeName(emp.getEmpName());
                details.setEmail(emp.getEmail());
                details.setCreatedDate(emp.getCreatedDate());
                details.setActive(emp.isActive());
                details.setAddress(emp.getAddress());
                details.setBod(emp.getBod());
                details.setPhone(emp.getPhoneNo());
                details.setRole(emp.getRoleName());
                detailList.add(details);
            }

            response.setEmployeeList(detailList);
            response.setMessage("successfully retrieve");
        }catch (ServiceException e){
            throw e;
        }
        return response;
    }
}

