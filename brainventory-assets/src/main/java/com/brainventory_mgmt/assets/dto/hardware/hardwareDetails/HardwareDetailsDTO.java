package com.brainventory_mgmt.assets.dto.hardware.hardwareDetails;

import com.brainventory_mgmt.assets.dto.hardware.hardwareBrand.HardwareBrandDTO;
import com.brainventory_mgmt.assets.dto.hardware.hardwareLine.HardwareLineDTO;
import com.brainventory_mgmt.assets.dto.hardware.hardwareName.HardwareNameDTO;
import com.brainventory_mgmt.assets.dto.hardware.hardwareSerie.HardwareSerieDTO;
import com.brainventory_mgmt.assets.enums.HardwareOperationalStatus;
import com.brainventory_mgmt.assets.enums.HardwarePhysicalStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HardwareDetailsDTO {
    Long idHardwareDetails;

    HardwareNameDTO hardwareName;
    HardwareBrandDTO hardwareBrand;
    HardwareLineDTO hardwareLine;
    HardwareSerieDTO hardwareSerie;

    @NotBlank
    String serialNumber;

    String description;

    @NotNull
    HardwarePhysicalStatus physicalStatus;

    @NotNull
    HardwareOperationalStatus operationalStatus;

    @NotNull
    LocalDate purchaseDate;

    @NotNull
    LocalDate warrantyEndDate;
}
