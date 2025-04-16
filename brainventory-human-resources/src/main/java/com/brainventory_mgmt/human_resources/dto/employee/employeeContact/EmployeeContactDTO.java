package com.brainventory_mgmt.human_resources.dto.employee.employeeContact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class EmployeeContactDTO {
    Long id;

    @NotBlank
    @Size(min = 10, max = 25)
    String phoneNumber;

    @NotBlank
    @Size(min = 5 , max = 255)
    @Email
    String email;
}
