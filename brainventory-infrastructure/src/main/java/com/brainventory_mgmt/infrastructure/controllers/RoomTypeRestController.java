package com.brainventory_mgmt.infrastructure.controllers;

import com.brainventory_mgmt.infrastructure.dto.room.roomType.RoomTypeDTO;
import com.brainventory_mgmt.infrastructure.services.intefaces.IRoomTypeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/room-type")
@RequiredArgsConstructor
public class RoomTypeRestController {

    private final IRoomTypeService roomTypeService;

    @GetMapping("/references")
    public ResponseEntity<List<RoomTypeDTO>> findAllReferences(){
        return new ResponseEntity<>(roomTypeService.findAllReferences(), HttpStatus.OK);
    }
}
