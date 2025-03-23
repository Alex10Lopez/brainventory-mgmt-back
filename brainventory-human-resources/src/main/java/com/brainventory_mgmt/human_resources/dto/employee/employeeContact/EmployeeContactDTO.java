package com.brainventory_mgmt.human_resources.dto.employee.employeeContact;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeContactDTO {
    Long id;

    @NotBlank
    String phoneNumber;

    @NotBlank
    String email;
}
