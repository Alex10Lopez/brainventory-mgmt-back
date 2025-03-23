package com.brainventory_mgmt.infrastructure.dto.building;

import com.brainventory_mgmt.infrastructure.dto.building.buildingAddress.BuildingAddressDTO;
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
public class BuildingDTO {
    Long id;
    String image;

    @NotBlank
    String name;

    @NotBlank
    Integer numberOfFloors;

    String description;

    BuildingAddressDTO address;
}
