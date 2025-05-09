package com.brainventory_mgmt.assets.services.impl;

import com.brainventory_mgmt.assets.client.RoomServiceClient;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceListDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceReferenceDTO;
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
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public ITDeviceRequestDTO saveITDevice(ITDeviceRequestDTO itDeviceRequestDTO, MultipartFile image) {
        try {
            ITDeviceEntity itDevice = modelMapper.map(itDeviceRequestDTO, ITDeviceEntity.class);

            if (itDeviceRequestDTO.getRoom() != null && itDeviceRequestDTO.getRoom().getId() != null) {
                DeviceRoomDTO deviceRoomDTO = roomServiceClient.linkRoomById(itDeviceRequestDTO.getRoom().getId());
                itDevice.setIdRoom(deviceRoomDTO.getId());
            }

            if (itDevice.getHardwareDetails() != null) {
                HardwareDetailsEntity savedHardwareDetails = modelMapper.map(itDeviceRequestDTO.getHardwareDetails(), HardwareDetailsEntity.class);

                itDevice.setHardwareDetails(savedHardwareDetails);
                savedHardwareDetails.setItDevice(itDevice);

                hardwareDetailsRepository.save(savedHardwareDetails);

                if (image != null && !image.isEmpty()) {
                    String folderPath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt/images/assets/it-device/";
                    String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                    Path path = Paths.get(folderPath + filename);
                    Files.createDirectories(path.getParent());
                    Files.write(path, image.getBytes());
                    itDevice.setImage("/images/assets/it-device/" + filename);
                }
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

        if (itDeviceDTO.getRoom() != null && itDeviceDTO.getRoom().getId() != null) {
            RoomReferenceDTO roomReferenceDTO = roomServiceClient.findRoomById(itDeviceDTO.getRoom().getId());
            itDeviceDTO.setRoom(roomReferenceDTO);
        }

        return itDeviceDTO;
    }

    @Override
    public ITDeviceRequestDTO updateITDevice(ITDeviceRequestDTO itDeviceRequestDTO, MultipartFile image, Long id) {
        ITDeviceEntity existingITDevice = itDeviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("IT Device not found!"));

        ITDeviceEntity updatedITDevice = modelMapper.map(itDeviceRequestDTO, ITDeviceEntity.class);
        updatedITDevice.setId(id);

        if (image != null && !image.isEmpty()) {
            try {
                String folderPath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt/images/assets/it-device/";
                String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path path = Paths.get(folderPath + filename);
                Files.createDirectories(path.getParent());
                Files.write(path, image.getBytes());
                updatedITDevice.setImage("/images/assets/it-device/" + filename);

                if (existingITDevice.getImage() != null && !existingITDevice.getImage().isBlank()) {
                    String basePath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt";
                    Path oldImagePath = Paths.get(basePath + existingITDevice.getImage());
                    Files.deleteIfExists(oldImagePath);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error updating employee image: " + e.getMessage());
            }
        } else {
            updatedITDevice.setImage(existingITDevice.getImage());
        }

        if (itDeviceRequestDTO.getRoom() != null && itDeviceRequestDTO.getRoom().getId() != null) {
            DeviceRoomDTO deviceRoomDTO = roomServiceClient.linkRoomById(itDeviceRequestDTO.getRoom().getId());
            updatedITDevice.setIdRoom(deviceRoomDTO.getId());
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
                .orElseThrow(() -> new RuntimeException("IT Device not found"));

        if (itDevice.getImage() != null && !itDevice.getImage().isBlank()) {
            String basePath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt";
            Path imagePath = Paths.get(basePath + itDevice.getImage());
            try {
                Files.deleteIfExists(imagePath);
            } catch (Exception e) {
                throw new RuntimeException("Error deleting IT Device image: " + e.getMessage());
            }
        }

        HardwareDetailsEntity hardwareDetails = itDevice.getHardwareDetails();

        if (hardwareDetails != null)
            hardwareDetailsRepository.delete(hardwareDetails);
    }

    @Override
    public List<ITDeviceReferenceDTO> findAllITDevices(){
        return itDeviceRepository.findAll()
                .stream()
                .map(itDevice -> modelMapper.map(itDevice, ITDeviceReferenceDTO.class))
                .collect(Collectors.toList());
    }
}