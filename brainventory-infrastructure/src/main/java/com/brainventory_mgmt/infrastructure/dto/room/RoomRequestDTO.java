package com.brainventory_mgmt.infrastructure.dto.room;

import com.brainventory_mgmt.infrastructure.dto.building.RoomBuildingDTO;
import com.brainventory_mgmt.infrastructure.dto.department.DepartmentReferenceDTO;
import com.brainventory_mgmt.infrastructure.dto.department.RoomDepartmentDTO;
import com.brainventory_mgmt.infrastructure.dto.room.roomType.RoomRoomTypeDTO;
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

    RoomRoomTypeDTO roomType;

    @NotBlank
    String name;

    Integer capacityMax;

    RoomBuildingDTO building;

    @NotBlank
    String floorLabel;

    String description;

    List<RoomDepartmentDTO> departments;
}
