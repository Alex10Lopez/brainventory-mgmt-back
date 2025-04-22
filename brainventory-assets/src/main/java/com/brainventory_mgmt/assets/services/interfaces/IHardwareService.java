package com.brainventory_mgmt.assets.services.interfaces;


import com.brainventory_mgmt.assets.dto.hardware.brand.BrandDTO;
import com.brainventory_mgmt.assets.dto.hardware.line.LineDTO;
import com.brainventory_mgmt.assets.dto.hardware.name.NameDTO;
import com.brainventory_mgmt.assets.dto.hardware.serie.SerieDTO;

import java.util.List;

public interface IHardwareService {
    List<NameDTO> findAllITDeviceNames();

    List<NameDTO> findAllIODeviceNames();

    List<BrandDTO> findAllHardwareBrands();

    List<LineDTO> findAllITDeviceLines();

    List<LineDTO> findAllIODeviceLines();

    List<SerieDTO> findAllHardwareSeries();
}
