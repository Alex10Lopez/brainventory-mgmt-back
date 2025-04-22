package com.brainventory_mgmt.assets.dto.hardware.hardwareDetails;

import com.brainventory_mgmt.assets.dto.hardware.brand.BrandDTO;
import com.brainventory_mgmt.assets.dto.hardware.line.LineDTO;
import com.brainventory_mgmt.assets.dto.hardware.name.NameDTO;
import com.brainventory_mgmt.assets.dto.hardware.serie.SerieDTO;
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

    NameDTO hardwareName;
    BrandDTO hardwareBrand;
    LineDTO hardwareLine;
    SerieDTO hardwareSerie;

    String serialNumber;
}
