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
import com.cy.leaveAppNative.reqres.ManagerRetrieveLeaveRequest;
import com.cy.leaveAppNative.reqres.ManagerRetrieveLeaveResponse;

import lombok.SneakyThrows;

@Service
public class ManagerRetrieveLeaveService {
    @Autowired
    AuthService authService;
    @Autowired
    LeaveRequestRepository leaveRequestRepository;

    @SneakyThrows
    public ManagerRetrieveLeaveResponse retrieveLeaveList (ManagerRetrieveLeaveRequest request) {
        ManagerRetrieveLeaveResponse response = new ManagerRetrieveLeaveResponse();
        try {
            if(!authService.checkRole(request.getUser(),"MANAGER")){
                throw new ServiceException("user is not Manager");
            }

            List<LeaveRequestDTO> list = new ArrayList<>();
            // leaveRequestRepository.findByManagerApproveAndEmpId_ManagerIdOrderBySubmitDateDesc(false, request.getUser().getManagerId())
            // if(leaveRequestRepository.existsByManagerApproveAndEmpId_ManagerId(false, request.getUser().getManagerId())){
            //     throw new ServiceException("no record of leave under this manager yet");
            // }
            List<LeaveRequested> leaveRequestedList = 
            leaveRequestRepository.findByManagerApproveIsNullAndEmpId_ManagerIdOrderBySubmitDateDesc( request.getUser().getId());
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
