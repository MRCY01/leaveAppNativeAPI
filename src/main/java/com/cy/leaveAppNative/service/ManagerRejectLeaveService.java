package com.cy.leaveAppNative.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.leaveAppNative.entity.LeaveBalance;
import com.cy.leaveAppNative.entity.LeaveRequested;
import com.cy.leaveAppNative.exeption.ServiceException;
import com.cy.leaveAppNative.jwt.AuthService;
import com.cy.leaveAppNative.repo.LeaveBalanceRepository;
import com.cy.leaveAppNative.repo.LeaveRequestRepository;
import com.cy.leaveAppNative.reqres.ManagerRejectLeaveRequest;
import com.cy.leaveAppNative.reqres.ManagerRejectLeaveResponse;

import lombok.SneakyThrows;

@Service
public class ManagerRejectLeaveService {
    @Autowired
    AuthService authService;
    @Autowired
    LeaveRequestRepository leaveRequestRepository;
    @Autowired
    LeaveBalanceRepository leaveBalanceRepository;

    @SneakyThrows
    public ManagerRejectLeaveResponse rejectLeave(ManagerRejectLeaveRequest request) {
        ManagerRejectLeaveResponse response = new ManagerRejectLeaveResponse();
        try {
            if (!authService.checkRole(request.getUser(), "MANAGER")) {
                throw new ServiceException("user is not Manager");
            }

            LeaveRequested leaveRequested = leaveRequestRepository.findById(Long.parseLong(request.getLeaveRequestId()))
                    .orElseThrow(() -> new ServiceException(" Leave requested not found"));

            if(!leaveRequested.getManagerApprove()){
                throw new ServiceException("Leave is already rejected ");
            }
            if (leaveRequested.getManagerApprove()) {
                throw new ServiceException("Leave is already approved");
            }
            LeaveBalance leaveBalance = leaveBalanceRepository.findById(leaveRequested.getLeaveBalance().getId())
                    .orElseThrow(() -> new ServiceException("leave Balance not found"));

            leaveRequested.setRejectedReason(request.getRejectReason());
            leaveRequested.setStatus("Manager Reject");
            Float rejectDays = null;
            if (leaveRequested.getHalfday().equalsIgnoreCase("NO")) {
                rejectDays = (float) (Float.parseFloat(leaveBalance.getBalance()) + 1);
            } else if (leaveRequested.getHalfday().equalsIgnoreCase("MORNING")
                    || leaveRequested.getHalfday().equalsIgnoreCase("AFTERNOON")) {
                rejectDays = (float) (Float.parseFloat(leaveBalance.getBalance()) + 0.5);
            } else {
                throw new ServiceException("Wrong Half day value");
            }
            leaveBalance.setBalance(rejectDays.toString());

            leaveRequestRepository.save(leaveRequested);
            leaveBalanceRepository.save(leaveBalance);

            response.setMessage("succesfully rejected");
            response.setStatusCode("200");
        } catch (ServiceException e) {
            throw e;
        }
        return response;
    }
}
