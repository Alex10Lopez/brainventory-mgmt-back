package com.brainventory_mgmt.assets.services.interfaces;

import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceDTO;
import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceListDTO;
import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceRequestDTO;

import java.util.List;

public interface IIODeviceService {
    IODeviceRequestDTO saveIODevice(IODeviceRequestDTO ioDeviceRequestDTO);
    List<IODeviceListDTO> findAll();
    IODeviceDTO findById(Long id);
    IODeviceRequestDTO updateIODevice(IODeviceRequestDTO ioDeviceRequestDTO, Long id);
    void deleteIODevice(Long id);
}
