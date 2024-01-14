package com.cy.leaveAppNative.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.leaveAppNative.entity.LeaveBalance;
import com.cy.leaveAppNative.entity.LeaveRequested;
import com.cy.leaveAppNative.exeption.ServiceException;
import com.cy.leaveAppNative.jwt.AuthService;
import com.cy.leaveAppNative.repo.LeaveBalanceRepository;
import com.cy.leaveAppNative.repo.LeaveRequestRepository;
import com.cy.leaveAppNative.reqres.ManagerApproveLeaveRequest;
import com.cy.leaveAppNative.reqres.ManagerApproveLeaveResponse;
import com.cy.leaveAppNative.reqres.ManagerRejectLeaveRequest;
import com.cy.leaveAppNative.reqres.ManagerRejectLeaveResponse;

import lombok.SneakyThrows;

@Service
public class ManagerApproveLeaveService {
    @Autowired
    AuthService authService;
    @Autowired
    LeaveRequestRepository leaveRequestRepository;
    @Autowired
    LeaveBalanceRepository leaveBalanceRepository;

    @SneakyThrows
    public ManagerApproveLeaveResponse approveLeave(ManagerApproveLeaveRequest request) {
        ManagerApproveLeaveResponse response = new ManagerApproveLeaveResponse();
        try {
            if (!authService.checkRole(request.getUser(), "MANAGER")) {
                throw new ServiceException("user is not Manager");
            }

            LeaveRequested leaveRequested = leaveRequestRepository.findById(Long.parseLong(request.getLeaveRequestId()))
                    .orElseThrow(() -> new ServiceException(" Leave requested not found"));

            if (!leaveRequested.getManagerApprove()) {
                throw new ServiceException("Leave is already rejected ");
            }
            if (leaveRequested.getManagerApprove()) {
                throw new ServiceException("Leave is already approved");
            }
            leaveRequested.setManagerApprove(true);
            leaveRequested.setStatus("Manager Approved");

            leaveRequestRepository.save(leaveRequested);
            response.setMessage("succesfully approve");
            response.setStatusCode("200");
        } catch (ServiceException e) {
            throw e;
        }
        return response;
    }
}
