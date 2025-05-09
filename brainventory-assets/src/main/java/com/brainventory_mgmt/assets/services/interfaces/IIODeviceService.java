package com.brainventory_mgmt.assets.services.interfaces;

import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceDTO;
import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceListDTO;
import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IIODeviceService {
    IODeviceRequestDTO saveIODevice(IODeviceRequestDTO ioDeviceRequestDTO, MultipartFile image);
    List<IODeviceListDTO> findAll();
    IODeviceDTO findById(Long id);
    IODeviceRequestDTO updateIODevice(IODeviceRequestDTO ioDeviceRequestDTO, MultipartFile image, Long id);
    void deleteIODevice(Long id);
}
