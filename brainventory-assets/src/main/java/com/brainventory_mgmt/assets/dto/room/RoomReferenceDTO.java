package com.brainventory_mgmt.assets.dto.room;

import com.brainventory_mgmt.assets.enums.RoomTypes;
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
    Long id;

    @NotBlank
    RoomTypes roomType;

    String name;
    String number;
}
