package com.brainventory_mgmt.assets.services.interfaces;

import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceListDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceReferenceDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IITDeviceService {
    ITDeviceRequestDTO saveITDevice(ITDeviceRequestDTO itDeviceRequestDTO, MultipartFile image);
    List<ITDeviceListDTO> findAll();
    ITDeviceDTO findById(Long id);
    ITDeviceRequestDTO updateITDevice(ITDeviceRequestDTO itDeviceRequestDTO, MultipartFile image, Long id);
    void deleteITDevice(Long id);

    List<ITDeviceReferenceDTO> findAllITDevices();
}
