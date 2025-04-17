package com.brainventory_mgmt.infrastructure.dto.building;

import com.brainventory_mgmt.infrastructure.dto.building.buildingAddress.BuildingAddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
    @Size(min = 1, max = 50)
    String name;

    @NotNull
    @Positive
    @Min(1)
    Integer numberOfFloors;

    @Size(max = 500)
    String description;

    @Valid
    BuildingAddressDTO address;
}
