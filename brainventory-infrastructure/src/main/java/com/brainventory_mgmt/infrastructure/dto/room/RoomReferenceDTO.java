package com.brainventory_mgmt.infrastructure.dto.room;

import com.brainventory_mgmt.infrastructure.enums.RoomTypes;
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
    RoomTypes roomType;
    String name;
    String number;
}
