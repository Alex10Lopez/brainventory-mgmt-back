package com.brainventory_mgmt.assets.dto.ioDevice;


import com.brainventory_mgmt.assets.dto.hardware.hardwareDetails.HardwareDetailsDTO;
import com.brainventory_mgmt.assets.dto.hardware.hardwareDetails.HardwareDetailsReferenceDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceReferenceDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IODeviceListDTO {
    Long id;
    String image;

    HardwareDetailsReferenceDTO hardwareDetails;
}
