package com.brainventory_mgmt.auth.dto;

import com.brainventory_mgmt.auth.dto.contact.ContactLoginDTO;
import com.brainventory_mgmt.auth.dto.contact.ContactRegisterDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {
    @NotBlank
    String email;

    @NotBlank
    String password;
}
