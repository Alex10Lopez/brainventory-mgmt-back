package com.brainventory_mgmt.assets.services.impl;

import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceDTO;
import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceListDTO;
import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceRequestDTO;
import com.brainventory_mgmt.assets.models.hardware.hardwareDetails.HardwareDetailsEntity;
import com.brainventory_mgmt.assets.models.ioDevice.IODeviceEntity;
import com.brainventory_mgmt.assets.models.itDevice.ITDeviceEntity;
import com.brainventory_mgmt.assets.repository.IHardwareDetailsRepository;
import com.brainventory_mgmt.assets.repository.IIODeviceRepository;
import com.brainventory_mgmt.assets.services.interfaces.IIODeviceService;
import lombok.AllArgsConstructor;
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
public class IODeviceServiceImpl implements IIODeviceService {
    private final IIODeviceRepository ioDeviceRepository;
    private final IHardwareDetailsRepository hardwareDetailsRepository;
    private final ModelMapper modelMapper;

    @Override
    public IODeviceRequestDTO saveIODevice(IODeviceRequestDTO ioDeviceRequestDTO, MultipartFile image) {
        try{
            IODeviceEntity ioDevice = modelMapper.map(ioDeviceRequestDTO,  IODeviceEntity.class);

            if(ioDevice.getHardwareDetails() != null){
                HardwareDetailsEntity savedHardwareDetails = modelMapper.map(ioDeviceRequestDTO.getHardwareDetails(), HardwareDetailsEntity.class);

                ioDevice.setHardwareDetails(savedHardwareDetails);
                savedHardwareDetails.setIoDevice(ioDevice);

                hardwareDetailsRepository.save(savedHardwareDetails);

                if (image != null && !image.isEmpty()) {
                    String folderPath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt/images/assets/io-device/";
                    String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                    Path path = Paths.get(folderPath + filename);
                    Files.createDirectories(path.getParent());
                    Files.write(path, image.getBytes());
                    ioDevice.setImage("/images/assets/io-device/" + filename);
                }
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
    public IODeviceRequestDTO updateIODevice(IODeviceRequestDTO ioDeviceRequestDTO, MultipartFile image, Long id) {
        IODeviceEntity existingIODevice = ioDeviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("IO Device not found!"));

        IODeviceEntity updatedIODevice = modelMapper.map(ioDeviceRequestDTO, IODeviceEntity.class);
        updatedIODevice.setId(id);

        if (image != null && !image.isEmpty()) {
            try {
                String folderPath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt/images/assets/io-device/";
                String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path path = Paths.get(folderPath + filename);
                Files.createDirectories(path.getParent());
                Files.write(path, image.getBytes());
                updatedIODevice.setImage("/images/assets/io-device/" + filename);

                if (existingIODevice.getImage() != null && !existingIODevice.getImage().isBlank()) {
                    String basePath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt";
                    Path oldImagePath = Paths.get(basePath + existingIODevice.getImage());
                    Files.deleteIfExists(oldImagePath);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error updating employee image: " + e.getMessage());
            }
        } else {
            existingIODevice.setImage(existingIODevice.getImage());
        }

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
                .orElseThrow(() -> new RuntimeException("IT Device not found"));

        if (ioDevice.getImage() != null && !ioDevice.getImage().isBlank()) {
            String basePath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt";
            Path imagePath = Paths.get(basePath + ioDevice.getImage());
            try {
                Files.deleteIfExists(imagePath);
            } catch (Exception e) {
                throw new RuntimeException("Error deleting IO Device image: " + e.getMessage());
            }
        }

        HardwareDetailsEntity hardwareDetails = ioDevice.getHardwareDetails();

        if (hardwareDetails != null)
            hardwareDetailsRepository.delete(hardwareDetails);
    }
}
