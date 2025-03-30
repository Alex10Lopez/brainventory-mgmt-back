package com.brainventory_mgmt.assets.dto.itDevice;

import com.brainventory_mgmt.assets.dto.hardware.hardwareDetails.HardwareDetailsRequestDTO;
import com.brainventory_mgmt.assets.dto.room.DeviceRoomDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ITDeviceRequestDTO {
    Long id;

    String image;

    HardwareDetailsRequestDTO hardwareDetails;

    DeviceRoomDTO room;
}
