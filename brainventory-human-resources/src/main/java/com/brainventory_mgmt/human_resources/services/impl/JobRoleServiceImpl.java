package com.brainventory_mgmt.human_resources.services.impl;

import com.brainventory_mgmt.human_resources.dto.jobRole.JobRoleReferenceDTO;
import com.brainventory_mgmt.human_resources.repository.IJobRoleRepository;
import com.brainventory_mgmt.human_resources.services.interfaces.IJobRoleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobRoleServiceImpl implements IJobRoleService {
    private final IJobRoleRepository jobRoleRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<JobRoleReferenceDTO> findAllReferences() {
        return jobRoleRepository.findAll()
                .stream()
                .map(jobRole -> modelMapper.map(jobRole, JobRoleReferenceDTO.class))
                .collect(Collectors.toList());
    }
}
