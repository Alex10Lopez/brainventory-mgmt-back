package com.brainventory_mgmt.assets.dto.hardware.hardwareDetails;

import com.brainventory_mgmt.assets.dto.hardware.brand.HardwareDetailsBrandDTO;
import com.brainventory_mgmt.assets.dto.hardware.line.HardwareDetailsLineDTO;
import com.brainventory_mgmt.assets.dto.hardware.name.HardwareDetailsNameDTO;
import com.brainventory_mgmt.assets.dto.hardware.serie.HardwareDetailsSerieDTO;
import com.brainventory_mgmt.assets.enums.HardwareOperationalStatus;
import com.brainventory_mgmt.assets.enums.HardwarePhysicalStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Validated
public class HardwareDetailsRequestDTO {
    Long idHardwareDetails;

    @Valid
    HardwareDetailsNameDTO hardwareName;

    @Valid
    HardwareDetailsBrandDTO hardwareBrand;

    @Valid
    HardwareDetailsLineDTO hardwareLine;

    @Valid
    HardwareDetailsSerieDTO hardwareSerie;

    @NotBlank
    String serialNumber;

    @Size(max = 500)
    String description;

    @NotNull
    HardwarePhysicalStatus physicalStatus;

    @NotNull
    HardwareOperationalStatus operationalStatus;

    @NotNull
    @PastOrPresent
    LocalDate purchaseDate;

    @NotNull
    LocalDate warrantyEndDate;
}
