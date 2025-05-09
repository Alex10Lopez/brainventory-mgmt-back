package com.brainventory_mgmt.infrastructure.services.intefaces;

import com.brainventory_mgmt.infrastructure.dto.building.BuildingReferenceDTO;
import com.brainventory_mgmt.infrastructure.dto.room.DeviceRoomDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomReferenceDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRoomService {
    RoomRequestDTO saveRoom(RoomRequestDTO roomRequestDTO, MultipartFile image);
    List<RoomDTO> findAll();
    RoomDTO findById(Long id);
    RoomRequestDTO updateRoom(RoomRequestDTO roomRequestDTO, MultipartFile image, Long id);
    void deleteRoom(Long id);

    List<RoomReferenceDTO> findAllRooms();
}
