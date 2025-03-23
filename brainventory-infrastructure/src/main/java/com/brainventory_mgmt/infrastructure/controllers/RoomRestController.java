package com.brainventory_mgmt.infrastructure.controllers;

import com.brainventory_mgmt.infrastructure.dto.room.RoomDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomRequestDTO;
import com.brainventory_mgmt.infrastructure.services.intefaces.IRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomRestController {
    private final IRoomService roomService;

    public RoomRestController(IRoomService roomService){
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<RoomRequestDTO> saveRoom(@RequestBody RoomRequestDTO roomRequestDTO){
        return new ResponseEntity<>(roomService.saveRoom(roomRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> findAll(){
        return new ResponseEntity<>(roomService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(roomService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomRequestDTO> updateRoom(@RequestBody RoomRequestDTO roomRequestDTO, @PathVariable Long id){
        return new ResponseEntity<>(roomService.updateRoom(roomRequestDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RoomDTO> deleteRoom(@PathVariable Long id){
        try {
            roomService.deleteRoom(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
