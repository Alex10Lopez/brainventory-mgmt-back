package com.brainventory_mgmt.infrastructure.dto.room;

import com.brainventory_mgmt.infrastructure.dto.building.RoomBuildingDTO;
import com.brainventory_mgmt.infrastructure.dto.department.DepartmentReferenceDTO;
import com.brainventory_mgmt.infrastructure.dto.department.RoomDepartmentDTO;
import com.brainventory_mgmt.infrastructure.enums.RoomTypes;
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
public class RoomRequestDTO {
    Long id;
    String image;

    @NotBlank
    RoomTypes roomType;

    String name;
    String number;
    Integer capacityMax;

    RoomBuildingDTO building;

    @NotBlank
    String floorLabel;

    String description;

    List<RoomDepartmentDTO> departments;
}
