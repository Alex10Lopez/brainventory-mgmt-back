package com.brainventory_mgmt.assets.services.impl;

import com.brainventory_mgmt.assets.client.RoomServiceClient;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceListDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceRequestDTO;
import com.brainventory_mgmt.assets.dto.room.DeviceRoomDTO;
import com.brainventory_mgmt.assets.dto.room.RoomReferenceDTO;
import com.brainventory_mgmt.assets.models.hardware.hardwareDetails.HardwareDetailsEntity;
import com.brainventory_mgmt.assets.models.itDevice.ITDeviceEntity;
import com.brainventory_mgmt.assets.repository.IHardwareDetailsRepository;
import com.brainventory_mgmt.assets.repository.IITDeviceRepository;
import com.brainventory_mgmt.assets.services.interfaces.IITDeviceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ITDeviceServiceImpl implements IITDeviceService {
    private final IITDeviceRepository itDeviceRepository;
    private final IHardwareDetailsRepository hardwareDetailsRepository;
    private final ModelMapper modelMapper;
    private final RoomServiceClient roomServiceClient;

    @Override
    public ITDeviceRequestDTO saveITDevice(ITDeviceRequestDTO itDeviceRequestDTO) {
        try {
            ITDeviceEntity itDevice = modelMapper.map(itDeviceRequestDTO, ITDeviceEntity.class);

            if (itDeviceRequestDTO.getRoom().getIdRoom() != null) {
                DeviceRoomDTO deviceRoomDTO = roomServiceClient.linkRoomById(itDeviceRequestDTO.getRoom().getIdRoom());
                itDevice.setIdRoom(deviceRoomDTO.getIdRoom());
            }

            if (itDevice.getHardwareDetails() != null) {
                HardwareDetailsEntity savedHardwareDetails = modelMapper.map(itDeviceRequestDTO.getHardwareDetails(), HardwareDetailsEntity.class);

                itDevice.setHardwareDetails(savedHardwareDetails);
                savedHardwareDetails.setItDevice(itDevice);

                hardwareDetailsRepository.save(savedHardwareDetails);
            }

            ITDeviceEntity savedITDevice = itDeviceRepository.save(itDevice);

            return modelMapper.map(savedITDevice, ITDeviceRequestDTO.class);
        } catch(Exception e){
            e.printStackTrace();
            throw new UnsupportedOperationException("Error saving the IT device!");
        }
    }

    @Override
    public List<ITDeviceListDTO> findAll() {
        return itDeviceRepository.findAll()
                .stream()
                .map(itDevice -> modelMapper.map(itDevice, ITDeviceListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ITDeviceDTO findById(Long id) {
        ITDeviceEntity itDevice = itDeviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("IT Device not found!"));

        ITDeviceDTO itDeviceDTO = modelMapper.map(itDevice, ITDeviceDTO.class);

        if (itDeviceDTO.getRoom().getIdRoom() != null) {
            RoomReferenceDTO roomReferenceDTO = roomServiceClient.findRoomById(itDeviceDTO.getRoom().getIdRoom());
            itDeviceDTO.setRoom(roomReferenceDTO);
        }

        return itDeviceDTO;
    }

    @Override
    public ITDeviceRequestDTO updateITDevice(ITDeviceRequestDTO itDeviceRequestDTO, Long id) {
        ITDeviceEntity existingITDevice = itDeviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("IT Device not found!"));

        ITDeviceEntity updatedITDevice = modelMapper.map(itDeviceRequestDTO, ITDeviceEntity.class);
        updatedITDevice.setIdITDevice(id);

        if (itDeviceRequestDTO.getRoom().getIdRoom() != null) {
            DeviceRoomDTO deviceRoomDTO = roomServiceClient.linkRoomById(itDeviceRequestDTO.getRoom().getIdRoom());
            updatedITDevice.setIdRoom(deviceRoomDTO.getIdRoom());
        }

        if (updatedITDevice.getHardwareDetails() != null) {
            HardwareDetailsEntity savedHardwareDetails = modelMapper.map(itDeviceRequestDTO.getHardwareDetails(), HardwareDetailsEntity.class);

            updatedITDevice.setHardwareDetails(savedHardwareDetails);
            savedHardwareDetails.setItDevice(updatedITDevice);

            hardwareDetailsRepository.save(savedHardwareDetails);
        }

        ITDeviceEntity savedITDevice = itDeviceRepository.save(updatedITDevice);

        return modelMapper.map(savedITDevice, ITDeviceRequestDTO.class);
    }

    @Override
    public void deleteITDevice(Long id) {
        ITDeviceEntity itDevice = itDeviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("IT Device not found!"));

        HardwareDetailsEntity hardwareDetails = itDevice.getHardwareDetails();

        if (hardwareDetails != null)
            hardwareDetailsRepository.delete(hardwareDetails);
    }
}