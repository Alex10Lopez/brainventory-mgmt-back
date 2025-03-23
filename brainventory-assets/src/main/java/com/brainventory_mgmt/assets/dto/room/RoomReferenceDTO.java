package com.brainventory_mgmt.assets.dto.room;

import com.brainventory_mgmt.assets.dto.room.roomType.RoomTypeDTO;
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
public class RoomReferenceDTO {
    Long idRoom;

    RoomTypeDTO roomType;

    @NotBlank
    String name;
}
