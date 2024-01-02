package com.cy.leaveAppNative.entity;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name", nullable = false)
    private String roleName;
    @Column(name = "active", nullable = false)
    private boolean active;
    @Column(name = "emp_name", nullable = false)
    private String empName;
    
    @Column(name = "created_date")
    private String createdDate;
    
    @Column(name = "approved_date")
    private String approvedDate;
    
    @NotBlank
    @Column(name= "password", nullable = false)
    private String password;
    
    @Column(name = "bod", nullable = false)
    private String bod;
    
    @Column(name = "address", nullable = false)
    private String address;
    
    @Column(name = "marital_status", nullable = false)
    private String maritalStatus;
    
    @NotBlank
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "phone_no", nullable = false)
    private String phoneNo;
    
    @Column(name = "employment_status")
    private String employmentStatus;
    
    @Column(name="firstTimeLogin")
    private boolean isFirstTimeLogin;

    //MANY TO ONE

    //ONE TO MANY
    @OneToMany(mappedBy = "employee")
    private List<LeaveBalance> leaveBalanceList;
    
    
}
