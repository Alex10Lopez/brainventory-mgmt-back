package com.brainventory_mgmt.infrastructure.controllers;

import com.brainventory_mgmt.infrastructure.dto.room.DeviceRoomDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomReferenceDTO;
import com.brainventory_mgmt.infrastructure.services.intefaces.IRoomDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room-device")
@RequiredArgsConstructor
public class RoomDeviceRestController {
    private final IRoomDeviceService roomDeviceService;

    @GetMapping("/link/{id}")
    public ResponseEntity<DeviceRoomDTO> linkRoomById(@PathVariable Long id){
        return new ResponseEntity<>(roomDeviceService.linkRoomById(id), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<RoomReferenceDTO> findRoomById(@PathVariable Long id){
        return new ResponseEntity<>(roomDeviceService.findRoomById(id), HttpStatus.OK);
    }
}
