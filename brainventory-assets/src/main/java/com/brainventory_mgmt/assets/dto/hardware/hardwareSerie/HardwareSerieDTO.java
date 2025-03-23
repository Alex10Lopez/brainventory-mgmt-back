package com.brainventory_mgmt.assets.dto.hardware.hardwareSerie;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HardwareSerieDTO {
    Long idHardwareSerie;
    String serie;
}
