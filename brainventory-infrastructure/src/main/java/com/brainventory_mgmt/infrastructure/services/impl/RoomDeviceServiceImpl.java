package com.brainventory_mgmt.infrastructure.services.impl;

import com.brainventory_mgmt.infrastructure.dto.room.DeviceRoomDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomReferenceDTO;
import com.brainventory_mgmt.infrastructure.models.room.RoomEntity;
import com.brainventory_mgmt.infrastructure.repository.IRoomRepository;
import com.brainventory_mgmt.infrastructure.services.intefaces.IRoomDeviceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomDeviceServiceImpl implements IRoomDeviceService {
    private final IRoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Override
    public DeviceRoomDTO linkRoomById(Long id) {
        RoomEntity room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found!"));

        return modelMapper.map(room, DeviceRoomDTO.class);
    }

    @Override
    public RoomReferenceDTO findRoomById(Long id) {
        RoomEntity room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found!"));

        return modelMapper.map(room, RoomReferenceDTO.class);
    }
}
