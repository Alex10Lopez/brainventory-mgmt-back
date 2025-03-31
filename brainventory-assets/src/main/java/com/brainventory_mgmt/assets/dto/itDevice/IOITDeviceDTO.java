package com.brainventory_mgmt.assets.dto.itDevice;

import com.brainventory_mgmt.assets.dto.hardware.hardwareDetails.DeviceHardwareDetailsDTO;
import com.brainventory_mgmt.assets.dto.hardware.hardwareDetails.HardwareDetailsDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IOITDeviceDTO {
    Long id;
}
