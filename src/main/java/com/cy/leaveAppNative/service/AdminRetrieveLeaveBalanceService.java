package com.cy.leaveAppNative.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.leaveAppNative.dto.LeaveBalanceDTO;
import com.cy.leaveAppNative.entity.LeaveBalance;
import com.cy.leaveAppNative.exeption.ServiceException;
import com.cy.leaveAppNative.repo.LeaveBalanceRepository;
import com.cy.leaveAppNative.reqres.AdminRetrieveLeaveBalanceRequest;
import com.cy.leaveAppNative.reqres.AdminRetrieveLeaveBalanceResponse;

import lombok.SneakyThrows;

@Service
public class AdminRetrieveLeaveBalanceService {
    @Autowired
    LeaveBalanceRepository leaveBalanceRepository;
    
    @SneakyThrows
    public AdminRetrieveLeaveBalanceResponse retrieveLeaveBalance (AdminRetrieveLeaveBalanceRequest request){
        AdminRetrieveLeaveBalanceResponse response = new AdminRetrieveLeaveBalanceResponse();
            try {
                List<LeaveBalance> leavebalanceList = leaveBalanceRepository.findAll();
                if(leavebalanceList.isEmpty()){
                    throw new ServiceException("empty leave Balance ");
                }
                float totalBalance = 0;
                List<LeaveBalanceDTO> leaveBalanceDTOList = new ArrayList<>();
                for(LeaveBalance lb : leavebalanceList){
                    LeaveBalanceDTO responseData = new LeaveBalanceDTO();
                    responseData.setEmpId(lb.getEmployee().getId().toString());
                    responseData.setEmpName(lb.getEmployee().getEmpName());
                    responseData.setEmpEmail(lb.getEmployee().getEmail());
                    responseData.setLeaveBalanceId(lb.getId());
                    responseData.setLeaveName(lb.getName());
                    responseData.setLeaveType(lb.getLeaveType());
                    responseData.setExpiryDate(lb.getExpiryDate());
                    responseData.setBalance(lb.getBalance());
                    totalBalance = Float.parseFloat(lb.getBalance())+totalBalance;
                    leaveBalanceDTOList.add(responseData);
                }
                response.setLeaveBalanceList(leaveBalanceDTOList);
                response.setTotalLeave(String.valueOf(totalBalance));
                response.setMessage("success");
                response.setStatusCode("200");
                
            } catch (ServiceException e) {
                throw e;
            }
        return response;
    }
}
