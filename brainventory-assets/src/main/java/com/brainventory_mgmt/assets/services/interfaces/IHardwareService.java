package com.brainventory_mgmt.assets.services.interfaces;


import com.brainventory_mgmt.assets.dto.hardware.hardwareBrand.HardwareBrandDTO;
import com.brainventory_mgmt.assets.dto.hardware.hardwareLine.HardwareLineDTO;
import com.brainventory_mgmt.assets.dto.hardware.hardwareName.HardwareNameDTO;
import com.brainventory_mgmt.assets.dto.hardware.hardwareSerie.HardwareSerieDTO;

import java.util.List;

public interface IHardwareService {
    List<HardwareNameDTO> findAllITDeviceNames();

    List<HardwareNameDTO> findAllIODeviceNames();

    List<HardwareBrandDTO> findAllHardwareBrands();

    List<HardwareLineDTO> findAllITDeviceLines();

    List<HardwareLineDTO> findAllIODeviceLines();

    List<HardwareSerieDTO> findAllHardwareSeries();
}
