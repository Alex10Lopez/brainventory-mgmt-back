package com.brainventory_mgmt.infrastructure.dto.building;

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
public class BuildingListDTO {
    Long id;
    String image;

    @NotBlank
    String name;

    @NotBlank
    Integer numberOfFloors;

    String description;
}
