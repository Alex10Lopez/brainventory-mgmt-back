package com.brainventory_mgmt.human_resources.dto.employee;

import com.brainventory_mgmt.human_resources.dto.employee.contact.ContactDTO;
import com.brainventory_mgmt.human_resources.dto.employee.jobRole.JobRoleDTO;
import com.brainventory_mgmt.human_resources.enums.EmployeePermissions;
import com.brainventory_mgmt.human_resources.enums.EmployeeSex;
import com.brainventory_mgmt.human_resources.enums.EmployeeStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeListDTO {
    Long id;
    String name;
    String lastname;
    LocalDate dateOfBirth;
    EmployeeSex sex;
    String nationality;
    EmployeePermissions permissions;

    List<JobRoleDTO> jobRoles;

    EmployeeStatus status;
    BigDecimal salary;

    List<ContactDTO> contacts;

    LocalDateTime loginDate;
}
