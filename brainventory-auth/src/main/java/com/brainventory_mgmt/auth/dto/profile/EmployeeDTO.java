package com.brainventory_mgmt.auth.dto.profile;

import com.brainventory_mgmt.auth.dto.profile.address.AddressDTO;
import com.brainventory_mgmt.auth.dto.profile.contact.ContactRequestDTO;
import com.brainventory_mgmt.auth.dto.profile.jobRole.JobRoleDTO;
import com.brainventory_mgmt.auth.enums.EmployeePermissions;
import com.brainventory_mgmt.auth.enums.EmployeeSex;
import com.brainventory_mgmt.auth.enums.EmployeeStatus;
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
    List<ContactRequestDTO> contacts;
    List<AddressDTO> addresses;
}
