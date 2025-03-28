package com.brainventory_mgmt.infrastructure.services.impl;

import com.brainventory_mgmt.infrastructure.dto.department.DepartmentReferenceDTO;
import com.brainventory_mgmt.infrastructure.dto.room.roomType.RoomTypeDTO;
import com.brainventory_mgmt.infrastructure.repository.IRoomTypeRepository;
import com.brainventory_mgmt.infrastructure.services.intefaces.IRoomTypeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomTypeServiceImpl implements IRoomTypeService {
    private final IRoomTypeRepository roomTypeRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<RoomTypeDTO> findAllReferences() {
        return roomTypeRepository.findAll()
                .stream()
                .map(roomType -> modelMapper.map(roomType, RoomTypeDTO.class))
                .collect(Collectors.toList());
    }
}
