package com.brainventory_mgmt.assets.services.impl;

import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceDTO;
import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceListDTO;
import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceRequestDTO;
import com.brainventory_mgmt.assets.models.hardware.hardwareDetails.HardwareDetailsEntity;
import com.brainventory_mgmt.assets.models.ioDevice.IODeviceEntity;
import com.brainventory_mgmt.assets.repository.IHardwareDetailsRepository;
import com.brainventory_mgmt.assets.repository.IIODeviceRepository;
import com.brainventory_mgmt.assets.services.interfaces.IIODeviceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IODeviceServiceImpl implements IIODeviceService {
    private final IIODeviceRepository ioDeviceRepository;
    private final IHardwareDetailsRepository hardwareDetailsRepository;
    private final ModelMapper modelMapper;

    @Override
    public IODeviceRequestDTO saveIODevice(IODeviceRequestDTO ioDeviceRequestDTO) {
        try{
            IODeviceEntity ioDevice = modelMapper.map(ioDeviceRequestDTO,  IODeviceEntity.class);

            if(ioDevice.getHardwareDetails() != null){
                HardwareDetailsEntity savedHardwareDetails = modelMapper.map(ioDeviceRequestDTO.getHardwareDetails(), HardwareDetailsEntity.class);

                ioDevice.setHardwareDetails(savedHardwareDetails);
                savedHardwareDetails.setIoDevice(ioDevice);

                hardwareDetailsRepository.save(savedHardwareDetails);
            }

            IODeviceEntity savedIODevice = ioDeviceRepository.save(ioDevice);

            return modelMapper.map(savedIODevice, IODeviceRequestDTO.class);
        } catch(Exception e){
            throw new UnsupportedOperationException("Error saving the IO device!");
        }
    }

    @Override
    public List<IODeviceListDTO> findAll() {
        return ioDeviceRepository.findAll()
                .stream()
                .map(ioDevice -> modelMapper.map(ioDevice, IODeviceListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public IODeviceDTO findById(Long id) {
        IODeviceEntity ioDevice = ioDeviceRepository.findById(id).orElseThrow(() -> new RuntimeException("IO Device not found!"));

        return modelMapper.map(ioDevice, IODeviceDTO.class);
    }

    @Override
    public IODeviceRequestDTO updateIODevice(IODeviceRequestDTO ioDeviceRequestDTO, Long id) {
        IODeviceEntity existingIODevice = ioDeviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("IO Device not found!"));

        IODeviceEntity updatedIODevice = modelMapper.map(ioDeviceRequestDTO, IODeviceEntity.class);
        updatedIODevice.setId(id);

        if (updatedIODevice.getHardwareDetails() != null) {
            HardwareDetailsEntity savedHardwareDetails = modelMapper.map(ioDeviceRequestDTO.getHardwareDetails(), HardwareDetailsEntity.class);

            updatedIODevice.setHardwareDetails(savedHardwareDetails);
            savedHardwareDetails.setIoDevice(updatedIODevice);

            hardwareDetailsRepository.save(savedHardwareDetails);
        }

        IODeviceEntity savedIODevice = ioDeviceRepository.save(updatedIODevice);

        return modelMapper.map(savedIODevice, IODeviceRequestDTO.class);
    }

    @Override
    public void deleteIODevice(Long id) {
        IODeviceEntity ioDevice = ioDeviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("IO Device not found!"));

        HardwareDetailsEntity hardwareDetails = ioDevice.getHardwareDetails();

        if (hardwareDetails != null)
            hardwareDetailsRepository.delete(hardwareDetails);
    }
}
