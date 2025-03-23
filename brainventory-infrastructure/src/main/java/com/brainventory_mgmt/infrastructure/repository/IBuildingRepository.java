package com.brainventory_mgmt.infrastructure.repository;

import com.brainventory_mgmt.infrastructure.models.building.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBuildingRepository extends JpaRepository<BuildingEntity, Long> {
}
