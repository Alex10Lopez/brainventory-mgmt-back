package com.brainventory_mgmt.infrastructure.dto.room;

import com.brainventory_mgmt.infrastructure.dto.building.RoomBuildingDTO;
import com.brainventory_mgmt.infrastructure.dto.department.RoomDepartmentDTO;
import com.brainventory_mgmt.infrastructure.enums.RoomTypes;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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

    @NotNull
    RoomTypes roomType;

    @Size(max = 50)
    String name;

    @Size(max = 10)
    String number;

    @Positive
    @Max(50)
    Integer capacityMax;

    RoomBuildingDTO building;

    @NotBlank
    String floorLabel;

    @Size(max = 500)
    String description;

    @Valid
    List<RoomDepartmentDTO> departments;
}
