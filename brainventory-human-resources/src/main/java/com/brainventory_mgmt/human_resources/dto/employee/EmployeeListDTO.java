package com.brainventory_mgmt.human_resources.dto.employee;

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
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeListDTO {
    Long id;

    @NotBlank
    @Size(min = 2, max = 70)
    String name;

    @NotBlank
    @Size(min = 2, max = 70)
    String lastname;

    @NotNull
    @Past
    LocalDate dateOfBirth;

    @NotNull
    EmployeeSex sex;

    @NotBlank
    @Size(min = 3, max = 50)
    String nationality;

    @NotNull
    EmployeePermissions permissions;

    List<JobRoleDTO> jobRoles;

    @NotNull
    EmployeeStatus status;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    @DecimalMin(value = "0.00")
    @DecimalMax(value = "99999999.99")
    BigDecimal salary;

    @Valid
    List<EmployeeContactDTO> contacts;

    LocalDateTime loginDate;
}
