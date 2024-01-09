package com.cy.leaveAppNative.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Setter
@Getter
@Table(name = "leave_request")
public class LeaveRequested {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_apply")
    private Date applyDate;

    @Column(name = "reason")
    private String reason;

    @Column(name = "manager_approve")
    private Boolean managerApprove;

    @Column(name = "submit_date")
    private String submitDate;

    @Column(name="status")
    private String status;
    
    @Column(name = "rejected_reason")
    private String rejectedReason;

    //MANY TO ONE
    @ManyToOne
    @JoinColumn(name = "leave_balance_id")
    private LeaveBalance leaveBalance;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee empId;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee managerId;

}
