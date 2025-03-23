package com.brainventory_mgmt.infrastructure.services.intefaces;

import com.brainventory_mgmt.infrastructure.dto.room.DeviceRoomDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomReferenceDTO;

public interface IRoomDeviceService {
    DeviceRoomDTO linkRoomById(Long id);
    RoomReferenceDTO findRoomById(Long id);
}
