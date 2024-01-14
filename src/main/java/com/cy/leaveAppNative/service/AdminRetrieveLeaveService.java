package com.cy.leaveAppNative.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.leaveAppNative.dto.LeaveRequestDTO;
import com.cy.leaveAppNative.entity.LeaveRequested;
import com.cy.leaveAppNative.exeption.ServiceException;
import com.cy.leaveAppNative.jwt.AuthService;
import com.cy.leaveAppNative.repo.LeaveRequestRepository;
import com.cy.leaveAppNative.reqres.AdminRetrieveLeaveRequest;
import com.cy.leaveAppNative.reqres.AdminRetrieveLeaveResponse;

import lombok.SneakyThrows;

@Service
public class AdminRetrieveLeaveService {
    @Autowired
    AuthService authService;
    @Autowired
    LeaveRequestRepository leaveRequestRepository;

    @SneakyThrows
    public AdminRetrieveLeaveResponse retrieveLeaveList (AdminRetrieveLeaveRequest request) {
        AdminRetrieveLeaveResponse response = new AdminRetrieveLeaveResponse();
        try {
            if(!authService.checkRole(request.getUser(),"ADMIN")){
                throw new ServiceException("user is not Admin");
            }

            List<LeaveRequestDTO> list = new ArrayList<>();
            List<LeaveRequested> leaveRequestedList = leaveRequestRepository.findAll();
            
            for(LeaveRequested l: leaveRequestedList){
                LeaveRequestDTO empLeave = new LeaveRequestDTO();
                empLeave.setEmpId(l.getEmpId().getId());
                empLeave.setId(l.getId());
                empLeave.setLeaveBalance(l.getLeaveBalance().getBalance());
                empLeave.setManagerApprove(l.getManagerApprove());
                empLeave.setApplyDate(l.getApplyDate());
                empLeave.setHalfDay(l.getHalfday());
                empLeave.setReason(l.getReason());
                empLeave.setSubmitDate(l.getSubmitDate());

                list.add(empLeave);
            }

            response.setRequestedList(list);
            response.setMessage("success");
            response.setStatusCode("200");
        } catch (ServiceException e) {
            throw e;
        }
        return response;
    }
}
