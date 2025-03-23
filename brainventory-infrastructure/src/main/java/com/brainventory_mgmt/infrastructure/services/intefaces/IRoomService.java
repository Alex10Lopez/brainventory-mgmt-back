package com.brainventory_mgmt.infrastructure.services.intefaces;

import com.brainventory_mgmt.infrastructure.dto.room.DeviceRoomDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomReferenceDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomRequestDTO;

import java.util.List;

public interface IRoomService {
    RoomRequestDTO saveRoom(RoomRequestDTO roomRequestDTO);
    List<RoomDTO> findAll();
    RoomDTO findById(Long id);
    RoomRequestDTO updateRoom(RoomRequestDTO roomRequestDTO, Long id);
    void deleteRoom(Long id);
}
