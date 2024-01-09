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
        // initialManager();
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
                adminDetails.setBod("1978/09/02");
                adminDetails.setPassword("Pass");
                adminDetails.setEmpName("adminKEN");
                adminDetails.setAddress("134, jln 33");
                adminDetails.setPhoneNo("0105613876");
                adminDetails.setEmploymentStatus("admin account");
                adminDetails.setCreatedDate(LocalDateTime.now().format(formatter));
                adminDetails.setRoleName("ADMIN");
                adminDetails.setManagerId(1L);
                employeeRepository.save(adminDetails);
                System.out.println("admin created");
                return adminDetails;
            }
        }
        catch (Exception e){
            System.out.println(e);
            throw e;
        }
    }

    @SneakyThrows
    private Employee initialManager(){
        try{
            {
                if(!employeeRepository.findAll().isEmpty()){
                    return null;
                }

                Employee managerDetails = new Employee();
                managerDetails.setActive(true);
                managerDetails.setEmail("manager@gmail.com");
                managerDetails.setBod("1990-5-6");
                managerDetails.setPassword("Pass");
                managerDetails.setEmpName("manager");
                managerDetails.setAddress("134, jln 33");
                managerDetails.setPhoneNo("0105624876");
                managerDetails.setEmploymentStatus("Employed");
                managerDetails.setCreatedDate(LocalDateTime.now().format(formatter));
                managerDetails.setRoleName("MANAGER");
                managerDetails.setManagerId(2L);
                employeeRepository.save(managerDetails);
                System.out.println("manager created");
                return managerDetails;
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
