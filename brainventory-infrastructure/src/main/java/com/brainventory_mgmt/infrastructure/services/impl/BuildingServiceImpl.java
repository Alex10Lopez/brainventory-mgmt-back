package com.brainventory_mgmt.infrastructure.services.impl;

import com.brainventory_mgmt.infrastructure.dto.building.BuildingDTO;
import com.brainventory_mgmt.infrastructure.dto.building.BuildingListDTO;
import com.brainventory_mgmt.infrastructure.models.building.BuildingEntity;
import com.brainventory_mgmt.infrastructure.repository.IBuildingRepository;
import com.brainventory_mgmt.infrastructure.services.intefaces.IBuildingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements IBuildingService {
    private final IBuildingRepository buildingRepository;
    private final ModelMapper modelMapper;

    public BuildingServiceImpl(IBuildingRepository buildingRepository, ModelMapper modelMapper){
        this.buildingRepository = buildingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BuildingDTO saveBuilding(BuildingDTO buildingDTO) {
        try{
            BuildingEntity building = modelMapper.map(buildingDTO, BuildingEntity.class);

            if(building.getAddress() != null)
                building.getAddress().setBuilding(building);

            BuildingEntity savedBuilding = buildingRepository.save(building);

            return modelMapper.map(savedBuilding, BuildingDTO.class);
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
    public BuildingDTO updateBuilding(BuildingDTO buildingDTO, Long id) {
        BuildingEntity existingBuilding = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found"));

        BuildingEntity updatedBuilding = modelMapper.map(buildingDTO, BuildingEntity.class);
        updatedBuilding.setId(id);

        if(updatedBuilding.getAddress() != null)
            updatedBuilding.getAddress().setBuilding(updatedBuilding);

        BuildingEntity savedBuilding = buildingRepository.save(updatedBuilding);

        return modelMapper.map(savedBuilding, BuildingDTO.class);
    }

    @Override
    public void deleteBuilding(Long id) {
        if(!buildingRepository.existsById(id))
            throw new RuntimeException("Building not found");

        buildingRepository.deleteById(id);
    }
}
