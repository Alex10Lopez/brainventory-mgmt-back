package com.brainventory_mgmt.human_resources.dto.employee;

import com.brainventory_mgmt.human_resources.dto.employee.employeeAddress.EmployeeAddressDTO;
import com.brainventory_mgmt.human_resources.dto.employee.employeeContact.EmployeeContactDTO;
import com.brainventory_mgmt.human_resources.dto.jobRole.JobRoleDTO;
import com.brainventory_mgmt.human_resources.enums.EmployeePermissions;
import com.brainventory_mgmt.human_resources.enums.EmployeeSex;
import com.brainventory_mgmt.human_resources.enums.EmployeeStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDTO {
    Long id;
    String image;
    String name;
    String lastname;
    LocalDate dateOfBirth;
    EmployeeSex sex;
    String nationality;
    EmployeePermissions permissions;
    EmployeeStatus status;
    BigDecimal salary;

    List<JobRoleDTO> jobRoles;
    List<EmployeeContactDTO> contacts;
    List<EmployeeAddressDTO> addresses;
}
