package com.brainventory_mgmt.infrastructure.services.intefaces;

import com.brainventory_mgmt.infrastructure.dto.building.BuildingDTO;
import com.brainventory_mgmt.infrastructure.dto.building.BuildingListDTO;
import com.brainventory_mgmt.infrastructure.dto.building.BuildingReferenceDTO;
import com.brainventory_mgmt.infrastructure.dto.building.BuildingRequestDTO;
import com.brainventory_mgmt.infrastructure.dto.room.DeviceRoomDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomReferenceDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBuildingService {
     BuildingRequestDTO saveBuilding(BuildingRequestDTO buildingRequestDTO, MultipartFile image);
     List<BuildingListDTO> findAll();
     BuildingDTO findById(Long id);
     BuildingRequestDTO updateBuilding(BuildingRequestDTO buildingRequestDTO, MultipartFile image, Long id);
     void deleteBuilding(Long id);

     List<BuildingReferenceDTO> findAllBuildings();
}
