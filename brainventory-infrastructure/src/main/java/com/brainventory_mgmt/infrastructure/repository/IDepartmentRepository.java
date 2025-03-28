package com.brainventory_mgmt.infrastructure.repository;

import com.brainventory_mgmt.infrastructure.models.department.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
