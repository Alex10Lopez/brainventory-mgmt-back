package com.brainventory_mgmt.infrastructure.services.intefaces;

import com.brainventory_mgmt.infrastructure.dto.building.BuildingDTO;
import com.brainventory_mgmt.infrastructure.dto.building.BuildingListDTO;
import com.brainventory_mgmt.infrastructure.dto.building.BuildingReferenceDTO;
import com.brainventory_mgmt.infrastructure.dto.room.DeviceRoomDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomReferenceDTO;

import java.util.List;

public interface IBuildingService {
     BuildingDTO saveBuilding(BuildingDTO buildingDTO);
     List<BuildingListDTO> findAll();
     BuildingDTO findById(Long id);
     BuildingDTO updateBuilding(BuildingDTO buildingDTO, Long id);
     void deleteBuilding(Long id);

     List<BuildingReferenceDTO> findAllReferences();
}
