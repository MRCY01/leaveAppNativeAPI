package com.cy.leaveAppNative.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.leaveAppNative.dto.LeaveHistoryDTO;
import com.cy.leaveAppNative.entity.Employee;
import com.cy.leaveAppNative.entity.LeaveRequested;
import com.cy.leaveAppNative.exeption.ServiceException;
import com.cy.leaveAppNative.repo.EmployeeRepository;
import com.cy.leaveAppNative.repo.LeaveRequestRepository;
import com.cy.leaveAppNative.reqres.RetrieveLeaveHistoryRequest;
import com.cy.leaveAppNative.reqres.RetrieveLeaveHistoryResponse;

import lombok.SneakyThrows;

@Service
public class RetrieveLeaveHistoryService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    LeaveRequestRepository leaveRequestRepository;
    
    // private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    
    @SneakyThrows
    public RetrieveLeaveHistoryResponse retrieveLeaveHistory(RetrieveLeaveHistoryRequest request){
        RetrieveLeaveHistoryResponse response = new RetrieveLeaveHistoryResponse();

            try {
                if(!employeeRepository.existsById(request.getUser().getId())){
                    throw new ServiceException("employee not exists");
                };
                List<LeaveHistoryDTO> leaveHistoryDTOList = new ArrayList<>();
                List<LeaveRequested> leaveRequestedList = leaveRequestRepository.findByEmpId(request.getUser());
                for(LeaveRequested l: leaveRequestedList){
                    LeaveHistoryDTO leaveHistoryDTO = new LeaveHistoryDTO();
                    leaveHistoryDTO.setSubmitDate(l.getSubmitDate());
                    leaveHistoryDTO.setDateApply(l.getApplyDate());
                    leaveHistoryDTO.setReason(l.getReason());
                    leaveHistoryDTO.setRejectedReason(l.getRejectedReason());
                    leaveHistoryDTO.setStatus(l.getStatus());
                    leaveHistoryDTO.setHalfday(l.getHalfday()); 

                    leaveHistoryDTOList.add(leaveHistoryDTO);
                }

                response.setLeaveHistoryDTO(leaveHistoryDTOList);
                response.setMessage("success");
                response.setStatusCode("200");
            } catch (ServiceException e) {
                throw e;
            }
        return response;
    }

}
