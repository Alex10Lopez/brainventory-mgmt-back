package com.brainventory_mgmt.human_resources.dto.employee;

import com.brainventory_mgmt.human_resources.dto.employee.employeeContact.EmployeeContactDTO;
import com.brainventory_mgmt.human_resources.dto.jobRole.JobRoleReferenceDTO;
import com.brainventory_mgmt.human_resources.enums.EmployeePermissions;
import com.brainventory_mgmt.human_resources.enums.EmployeeSex;
import com.brainventory_mgmt.human_resources.enums.EmployeeStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    String name;

    @NotBlank
    String lastname;

    @NotNull
    LocalDate dateOfBirth;

    @NotNull
    EmployeeSex sex;

    @NotBlank
    String nationality;

    @NotNull
    EmployeePermissions permissions;

    List<JobRoleReferenceDTO> jobRoles;

    @NotNull
    EmployeeStatus status;

    @NotBlank
    BigDecimal salary;

    List<EmployeeContactDTO> contacts;

    LocalDateTime loginDate;
}
