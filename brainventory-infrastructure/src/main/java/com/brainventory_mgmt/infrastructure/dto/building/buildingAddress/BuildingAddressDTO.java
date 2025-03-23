package com.brainventory_mgmt.infrastructure.dto.building.buildingAddress;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuildingAddressDTO {
    Long id;

    @NotBlank
    String street;

    @NotBlank
    String streetNumber;

    @NotBlank
    String postalCode;

    @NotBlank
    String city;

    @NotBlank
    String countryState;

    @NotBlank
    String country;

    String reference;
}
