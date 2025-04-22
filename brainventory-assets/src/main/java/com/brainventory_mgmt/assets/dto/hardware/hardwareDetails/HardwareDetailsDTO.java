package com.brainventory_mgmt.assets.dto.hardware.hardwareDetails;

import com.brainventory_mgmt.assets.dto.hardware.brand.BrandDTO;
import com.brainventory_mgmt.assets.dto.hardware.line.LineDTO;
import com.brainventory_mgmt.assets.dto.hardware.name.NameDTO;
import com.brainventory_mgmt.assets.dto.hardware.serie.SerieDTO;
import com.brainventory_mgmt.assets.enums.HardwareOperationalStatus;
import com.brainventory_mgmt.assets.enums.HardwarePhysicalStatus;
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

    NameDTO hardwareName;
    BrandDTO hardwareBrand;
    LineDTO hardwareLine;
    SerieDTO hardwareSerie;

    String serialNumber;
    String description;
    HardwarePhysicalStatus physicalStatus;
    HardwareOperationalStatus operationalStatus;
    LocalDate purchaseDate;
    LocalDate warrantyEndDate;
}
