package com.brainventory_mgmt.human_resources.repository;

import com.brainventory_mgmt.human_resources.models.employee.jobRoles.JobRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobRoleRepository extends JpaRepository<JobRoleEntity, Long> {
}
