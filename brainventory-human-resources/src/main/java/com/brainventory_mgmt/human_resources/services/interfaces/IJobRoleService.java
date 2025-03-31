package com.brainventory_mgmt.human_resources.services.interfaces;

import com.brainventory_mgmt.human_resources.dto.jobRole.JobRoleReferenceDTO;

import java.util.List;

public interface IJobRoleService {
    List<JobRoleReferenceDTO> findAllJobRoles();
}
