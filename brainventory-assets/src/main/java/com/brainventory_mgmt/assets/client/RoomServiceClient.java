package com.brainventory_mgmt.assets.client;

import com.brainventory_mgmt.assets.dto.room.DeviceRoomDTO;
import com.brainventory_mgmt.assets.dto.room.RoomReferenceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "brainventory-infrastructure", url = "http://localhost:9000/api/room-device")
public interface RoomServiceClient {
    @GetMapping("/link/{id}")
    DeviceRoomDTO linkRoomById(@PathVariable Long id);

    @GetMapping("/find/{id}")
    RoomReferenceDTO findRoomById(@PathVariable Long id);
}
