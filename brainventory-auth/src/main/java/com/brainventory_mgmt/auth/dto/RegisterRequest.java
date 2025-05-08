package com.brainventory_mgmt.auth.dto;

import com.brainventory_mgmt.auth.dto.profile.contact.ContactRequestDTO;
import com.brainventory_mgmt.auth.dto.profile.jobRole.JobRoleDTO;
import com.brainventory_mgmt.auth.enums.EmployeePermissions;
import com.brainventory_mgmt.auth.enums.EmployeeSex;
import com.brainventory_mgmt.auth.enums.EmployeeStatus;
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
public class RegisterRequest {
    String image;

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

    EmployeePermissions permissions;

    @NotNull
    EmployeeStatus status;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    @DecimalMin(value = "0.00")
    @DecimalMax(value = "99999999.99")
    BigDecimal salary;

    @Valid
    List<JobRoleDTO> jobRoles;

    List<ContactRequestDTO> contacts;

    @Size(min = 8, max = 255)
    String password;
}