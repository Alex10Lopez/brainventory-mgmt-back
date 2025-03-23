package com.brainventory_mgmt.auth.dto;

import com.brainventory_mgmt.auth.dto.contact.ContactRegisterDTO;
import com.brainventory_mgmt.auth.enums.EmployeePermissions;
import com.brainventory_mgmt.auth.enums.EmployeeSex;
import com.brainventory_mgmt.auth.enums.EmployeeStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    EmployeeStatus status;

    @NotBlank
    BigDecimal salary;

    List<ContactRegisterDTO> contacts;

    String password;
}
