package com.brainventory_mgmt.auth.dto.contact;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactLoginDTO {
    @NotBlank
    String email;
}
