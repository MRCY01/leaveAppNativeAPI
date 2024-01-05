package com.cy.leaveAppNative.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Setter
@Getter
@Table(name = "leave_balance")
public class LeaveBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "leave_balance_name")
    private String name;

    @Column(name = "balance")
    private String balance;

    @Column(name = "date_assigned")
    private String dateAssigned;

    @Column(name = "leave_type")
    private String leaveType;

    @Column(name = "expiry_date")
    private String expiryDate;

    // MANY TO ONE
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    // ONE TO MANY
    @OneToMany(mappedBy = "leaveBalance")
    private List<LeaveRequested> leave;


}
