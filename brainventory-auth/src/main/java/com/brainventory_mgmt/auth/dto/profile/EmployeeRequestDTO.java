package com.brainventory_mgmt.auth.dto.profile;

import com.brainventory_mgmt.auth.dto.profile.address.AddressDTO;
import com.brainventory_mgmt.auth.dto.profile.contact.ContactRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
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
public class EmployeeRequestDTO {
    Long id;
    String image;

    @Valid
    List<ContactRequestDTO> contacts;

    @Valid
    List<AddressDTO> addresses;

    @Size(min = 8, max = 255)
    String password;
}
