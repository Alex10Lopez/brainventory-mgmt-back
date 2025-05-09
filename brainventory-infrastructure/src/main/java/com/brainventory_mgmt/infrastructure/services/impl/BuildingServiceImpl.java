package com.brainventory_mgmt.infrastructure.services.impl;

import com.brainventory_mgmt.infrastructure.dto.building.BuildingDTO;
import com.brainventory_mgmt.infrastructure.dto.building.BuildingListDTO;
import com.brainventory_mgmt.infrastructure.dto.building.BuildingReferenceDTO;
import com.brainventory_mgmt.infrastructure.dto.building.BuildingRequestDTO;
import com.brainventory_mgmt.infrastructure.models.building.BuildingEntity;
import com.brainventory_mgmt.infrastructure.repository.IBuildingRepository;
import com.brainventory_mgmt.infrastructure.services.intefaces.IBuildingService;
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
public class BuildingServiceImpl implements IBuildingService {
    private final IBuildingRepository buildingRepository;
    private final ModelMapper modelMapper;


    @Override
    public BuildingRequestDTO saveBuilding(BuildingRequestDTO buildingRequestDTO, MultipartFile image) {
        try{
            BuildingEntity building = modelMapper.map(buildingRequestDTO, BuildingEntity.class);

            if(building.getAddress() != null)
                building.getAddress().setBuilding(building);

            if (image != null && !image.isEmpty()) {
                String folderPath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt/images/infrastructure/buildings/";
                String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path path = Paths.get(folderPath + filename);
                Files.createDirectories(path.getParent());
                Files.write(path, image.getBytes());
                building.setImage("/images/infrastructure/buildings/" + filename);
            }

            BuildingEntity savedBuilding = buildingRepository.save(building);

            return modelMapper.map(savedBuilding, BuildingRequestDTO.class);
        } catch (Exception e){
            throw new UnsupportedOperationException("Error saving the building!");
        }
    }

    @Override
    public List<BuildingListDTO> findAll() {
        return buildingRepository.findAll()
                .stream()
                .map(building -> modelMapper.map(building, BuildingListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity building = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found"));

        return modelMapper.map(building, BuildingDTO.class);
    }

    @Override
    public BuildingRequestDTO updateBuilding(BuildingRequestDTO buildingRequestDTO, MultipartFile image, Long id) {
        BuildingEntity existingBuilding = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found"));

        modelMapper.map(buildingRequestDTO, existingBuilding);
        existingBuilding.setId(id);
        String existingImage = existingBuilding.getImage();

        if (image != null && !image.isEmpty()) {
            try {
                String folderPath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt/images/infrastructure/buildings/";
                String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path path = Paths.get(folderPath + filename);
                Files.createDirectories(path.getParent());
                Files.write(path, image.getBytes());
                existingBuilding.setImage("/images/infrastructure/buildings/" + filename);

                if (existingBuilding.getImage() != null && !existingBuilding.getImage().isBlank()) {
                    String basePath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt";
                    Path oldImagePath = Paths.get(basePath + existingImage);
                    Files.deleteIfExists(oldImagePath);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error updating building image: " + e.getMessage());
            }
        }

        BuildingEntity savedBuilding = buildingRepository.save(existingBuilding);

        return modelMapper.map(savedBuilding, BuildingRequestDTO.class);
    }

    @Override
    public void deleteBuilding(Long id) {
        BuildingEntity building = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found"));

        if (building.getImage() != null && !building.getImage().isBlank()) {
            String basePath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt";
            Path imagePath = Paths.get(basePath + building.getImage());
            try {
                Files.deleteIfExists(imagePath);
            } catch (Exception e) {
                throw new RuntimeException("Error deleting building image: " + e.getMessage());
            }
        }

        buildingRepository.deleteById(id);
    }

    @Override
    public
    List<BuildingReferenceDTO> findAllBuildings(){
        return buildingRepository.findAll()
                .stream()
                .map(building -> modelMapper.map(building, BuildingReferenceDTO.class))
                .collect(Collectors.toList());
    }
}
