package com.brainventory_mgmt.human_resources.services.interfaces;

import com.brainventory_mgmt.human_resources.dto.employee.jobRole.JobRoleDTO;

import java.util.List;

public interface IJobRoleService {
    List<JobRoleDTO> findAllJobRoles();
}
