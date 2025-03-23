package com.brainventory_mgmt.assets.services.interfaces;

import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceListDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceRequestDTO;

import java.util.List;

public interface IITDeviceService {
    ITDeviceRequestDTO saveITDevice(ITDeviceRequestDTO itDeviceRequestDTO);
    List<ITDeviceListDTO> findAll();
    ITDeviceDTO findById(Long id);
    ITDeviceRequestDTO updateITDevice(ITDeviceRequestDTO itDeviceRequestDTO, Long id);
    void deleteITDevice(Long id);
}
