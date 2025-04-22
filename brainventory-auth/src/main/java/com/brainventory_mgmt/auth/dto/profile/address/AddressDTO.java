package com.brainventory_mgmt.auth.dto.profile.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Validated
public class AddressDTO {
    Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    String street;

    @Size(max = 20)
    String streetNumber;

    @NotBlank
    @Size(min = 3, max = 20)
    String postalCode;

    @NotBlank
    @Size(min = 2, max = 50)
    String city;

    @NotBlank
    @Size(min = 2, max = 50)
    String countryState;

    @NotBlank
    @Size(min = 2, max = 50)
    String country;

    @Size(max = 250)
    String reference;
}
