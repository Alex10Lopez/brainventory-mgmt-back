package com.brainventory_mgmt.auth.dto.profile.contact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequestDTO {
    Long id;

    @NotBlank
    @Size(min = 10, max = 25)
    String phoneNumber;

    @NotBlank
    @Size(min = 5 , max = 255)
    @Email
    String email;
}
