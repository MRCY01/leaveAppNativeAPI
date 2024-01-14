package com.cy.leaveAppNative.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.leaveAppNative.dto.LeaveApplyDTO;
import com.cy.leaveAppNative.entity.LeaveBalance;
import com.cy.leaveAppNative.entity.LeaveRequested;
import com.cy.leaveAppNative.exeption.ServiceException;
import com.cy.leaveAppNative.repo.LeaveBalanceRepository;
import com.cy.leaveAppNative.repo.LeaveRequestRepository;
import com.cy.leaveAppNative.reqres.ApplyLeaveRequest;
import com.cy.leaveAppNative.reqres.ApplyLeaveResponse;

import lombok.SneakyThrows;

@Service
public class ApplyLeaveService {
    @Autowired
    LeaveBalanceRepository leaveBalanceRepository;
    @Autowired
    LeaveRequestRepository leaveRequestRepository;

    @SneakyThrows
    public ApplyLeaveResponse applyLeave (ApplyLeaveRequest request){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        ApplyLeaveResponse response = new ApplyLeaveResponse();

        try {
            if(leaveBalanceRepository.findByEmployeeAndName(request.getUser(),request.getLeaveBalance())==null){
                throw new ServiceException("current user dont have this leave");
            }
            //check if balance enough
            LeaveBalance leaveBalance = leaveBalanceRepository.findByEmployeeAndName(request.getUser(),request.getLeaveBalance());
            float dayRemaining = Float.parseFloat(leaveBalance.getBalance());
            if(dayRemaining<=0){
                throw new ServiceException("no remaining Days");
            }
            //cal if enough date
            float calDays = dayRemaining - Float.parseFloat(request.getTotalApplyDays());
            if(calDays<0){
                throw new ServiceException("exceed balance for the leave");
            }
            
            List<LeaveApplyDTO> leaveApplyDTOList = request.getLeaveApplyDTOList();
            for(LeaveApplyDTO l: leaveApplyDTOList){
                LeaveRequested leaveRequested = new LeaveRequested();
                leaveRequested.setEmpId(request.getUser());
                leaveRequested.setStatus("Pending Approve");
                leaveRequested.setLeaveBalance(leaveBalance);
                leaveRequested.setSubmitDate(dtf.format(LocalDateTime.now()));
                String dateApplied = l.getDateApply();
                if(leaveRequestRepository.existsByEmpIdAndApplyDate(request.getUser(), dateApplied)){
                    throw new ServiceException("date is applied before");
                }
                leaveRequested.setApplyDate(l.getDateApply());
                leaveRequested.setHalfday(l.getHalfDay());
                leaveRequested.setReason(l.getReason());
                
                leaveRequestRepository.save(leaveRequested);
            }
            leaveBalance.setBalance(String.valueOf(calDays));
            leaveBalanceRepository.save(leaveBalance);
            response.setMessage("successfully apply");
            response.setStatusCode("200");
        } catch (ServiceException e) {
            throw e;
        }
        return response;
    }
}
