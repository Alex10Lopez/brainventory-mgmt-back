package com.brainventory_mgmt.assets.dto.itDevice;

import com.brainventory_mgmt.assets.dto.hardware.hardwareDetails.HardwareDetailsDTO;
import com.brainventory_mgmt.assets.dto.room.RoomReferenceDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ITDeviceDTO {
    Long id;
    String image;

    HardwareDetailsDTO hardwareDetails;

    RoomReferenceDTO room;
}
