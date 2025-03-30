package com.brainventory_mgmt.infrastructure.services.impl;

import com.brainventory_mgmt.infrastructure.dto.room.RoomDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomRequestDTO;
import com.brainventory_mgmt.infrastructure.models.room.RoomEntity;
import com.brainventory_mgmt.infrastructure.repository.IRoomRepository;
import com.brainventory_mgmt.infrastructure.services.intefaces.IRoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {
    private final IRoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Override
    public RoomRequestDTO saveRoom(RoomRequestDTO roomRequestDTO) {
        try {
            RoomEntity room = modelMapper.map(roomRequestDTO, RoomEntity.class);

            RoomEntity savedRoom = roomRepository.save(room);

            return modelMapper.map(savedRoom, RoomRequestDTO.class);
        } catch (Exception e){
            throw  new UnsupportedOperationException("Error saving the room!");
        }
    }

    @Override
    public List<RoomDTO> findAll() {
        return roomRepository.findAll()
                .stream()
                .map(room -> modelMapper.map(room, RoomDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDTO findById(Long id) {
        RoomEntity room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        return modelMapper.map(room, RoomDTO.class);
    }

    @Override
    public RoomRequestDTO updateRoom(RoomRequestDTO roomRequestDTO, Long id) {
        RoomEntity existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        RoomEntity updatedRoom = modelMapper.map(roomRequestDTO, RoomEntity.class);
        updatedRoom.setId(id);

        RoomEntity savedRoom = roomRepository.save(updatedRoom);

        return modelMapper.map(savedRoom, RoomRequestDTO.class);
    }

    @Override
    public void deleteRoom(Long id) {
        if(!roomRepository.existsById(id))
            throw new RuntimeException("Building not found");

        roomRepository.deleteById(id);
    }
}
