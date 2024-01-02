package com.cy.leaveAppNative;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.cy.leaveAppNative.entity.Employee;
import com.cy.leaveAppNative.repo.EmployeeRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataLoader implements ApplicationRunner {

    @Autowired
    private EmployeeRepository employeeRepository;
    // @Autowired
    // private LeaveTypeRepository leaveTypeRepository;

    public void run(ApplicationArguments args) {
        // initialRole();
        // initialLeaveType();
        initialAdmin();
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @SneakyThrows
    private Employee initialAdmin(){
        try{
            {
                if(!employeeRepository.findAll().isEmpty()){
                    return null;
                }

                Employee adminDetails = new Employee();
                adminDetails.setActive(true);
                adminDetails.setEmail("admin@gmail.com");
                adminDetails.setBod("1990-5-6");
                adminDetails.setPassword("Pass");
                adminDetails.setEmpName("admin");
                adminDetails.setAddress("134, jln 33");
                adminDetails.setMaritalStatus("married");
                adminDetails.setPhoneNo("0105613876");
                adminDetails.setEmploymentStatus("admin account");
                adminDetails.setCreatedDate(LocalDateTime.now().format(formatter));
                adminDetails.setRoleName("ADMIN");
                employeeRepository.save(adminDetails);

                // EmployeeRole tmp = new EmployeeRole();
                // tmp.setEmployee(adminDetails);
                // tmp.setRole(roleRepository.findByRoleName("ADMIN")
                //         .orElseThrow( () -> new EntityNotFoundException("ADMIN not found")));
                // tmp.setAssignedDate(LocalDateTime.now().format(formatter));
                // employeeRepository.save(adminDetails);
                // employeeRoleRepository.save(tmp);
                System.out.println("admin created");
                return adminDetails;
            }
        }
        catch (Exception e){
            System.out.println(e);
            throw e;
        }
    }
    // private void initialRole(){
    //     try{
    //         if(roleRepository.findAll().isEmpty()){
    //             String[] arr = new String[]{"ADMIN","MANAGER","EMPLOYEE"};
    //             for (String str: arr){
    //                 Role role = new Role();
    //                 role.setRoleName(str);
    //                 roleRepository.save(role);
    //             }
    //         }
    //     }catch (Exception e){
    //         System.out.println(e);
    //         throw e;
    //     }
    // }
    // private void initialLeaveType(){
    //     try{
    //         if(leaveTypeRepository.findAll().isEmpty()){
    //             String[] activeLeave = new String[]{"Annual Leave","Medical Leave","Birthday Leave","Replacement Leave"};
    //             for (String leave: activeLeave){
    //                 LeaveType leaveType = new LeaveType();
    //                 leaveType.setLeaveTypeName(leave);
    //                 leaveType.setActive(true);
    //                 leaveType.setExpiryDate("2023-12-31");
    //                 leaveType.setCreatedDate("2023-12-31");
    //                 leaveTypeRepository.save(leaveType);
    //             }
    //         }
    //     }catch (Exception e){
    //         System.out.println(e);
    //         throw e;
    //     }
    // }
}
