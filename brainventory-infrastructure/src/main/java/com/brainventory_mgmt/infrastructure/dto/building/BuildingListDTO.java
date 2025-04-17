package com.brainventory_mgmt.infrastructure.dto.building;

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
    String name;
    Integer numberOfFloors;
    String description;
}
