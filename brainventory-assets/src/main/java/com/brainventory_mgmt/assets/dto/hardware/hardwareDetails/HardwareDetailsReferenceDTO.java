package com.brainventory_mgmt.assets.dto.hardware.hardwareDetails;

import com.brainventory_mgmt.assets.dto.hardware.hardwareBrand.HardwareBrandDTO;
import com.brainventory_mgmt.assets.dto.hardware.hardwareLine.HardwareLineDTO;
import com.brainventory_mgmt.assets.dto.hardware.hardwareName.HardwareNameDTO;
import com.brainventory_mgmt.assets.dto.hardware.hardwareSerie.HardwareSerieDTO;
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
public class HardwareDetailsReferenceDTO {
    Long idHardwareDetails;

    HardwareNameDTO hardwareName;
    HardwareBrandDTO hardwareBrand;
    HardwareLineDTO hardwareLine;
    HardwareSerieDTO hardwareSerie;

    @NotBlank
    String serialNumber;
}
