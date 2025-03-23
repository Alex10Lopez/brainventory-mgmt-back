package com.brainventory_mgmt.infrastructure.dto.room;

import com.brainventory_mgmt.infrastructure.dto.building.BuildingReferenceDTO;
import com.brainventory_mgmt.infrastructure.dto.department.DepartmentReferenceDTO;
import com.brainventory_mgmt.infrastructure.dto.room.roomType.RoomTypeDTO;
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
public class RoomDTO {
    Long idRoom;
    String image;

    RoomTypeDTO roomType;

    @NotBlank
    String name;

    Integer capacityMax;

    @NotBlank
    String  floorLabel;

    String description;

    List<DepartmentReferenceDTO> departments;

    BuildingReferenceDTO building;
}