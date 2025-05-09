package com.brainventory_mgmt.infrastructure.controllers;

import com.brainventory_mgmt.infrastructure.dto.room.RoomDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomReferenceDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomRequestDTO;
import com.brainventory_mgmt.infrastructure.services.intefaces.IRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomRestController {
    private final IRoomService roomService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<RoomRequestDTO> saveRoom(@RequestPart("room") @Valid RoomRequestDTO roomRequestDTO,
                                                   @RequestPart(value = "image", required = false) MultipartFile image){
        return new ResponseEntity<>(roomService.saveRoom(roomRequestDTO, image), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> findAll(){
        return new ResponseEntity<>(roomService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(roomService.findById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<RoomRequestDTO> updateRoom(@RequestPart("room") @Valid RoomRequestDTO roomRequestDTO,
                                                     @RequestPart(value = "image", required = false) MultipartFile image,
                                                     @PathVariable Long id){
        return new ResponseEntity<>(roomService.updateRoom(roomRequestDTO, image, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id){
        try {
            roomService.deleteRoom(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/references")
    public ResponseEntity<List<RoomReferenceDTO>> findAllRooms(){
        return new ResponseEntity<>(roomService.findAllRooms(), HttpStatus.OK);
    }
}
