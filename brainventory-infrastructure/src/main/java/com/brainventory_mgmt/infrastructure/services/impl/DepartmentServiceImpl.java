package com.brainventory_mgmt.infrastructure.services.impl;

import com.brainventory_mgmt.infrastructure.dto.building.BuildingReferenceDTO;
import com.brainventory_mgmt.infrastructure.dto.department.DepartmentReferenceDTO;
import com.brainventory_mgmt.infrastructure.repository.IDepartmentRepository;
import com.brainventory_mgmt.infrastructure.services.intefaces.IDepartmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements IDepartmentService {
    private final IDepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<DepartmentReferenceDTO> findAllReferences() {
        return departmentRepository.findAll()
                .stream()
                .map(department -> modelMapper.map(department, DepartmentReferenceDTO.class))
                .collect(Collectors.toList());
    }
}
