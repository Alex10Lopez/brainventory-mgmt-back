package com.brainventory_mgmt.assets.repository;

import com.brainventory_mgmt.assets.models.hardware.hardwareBrand.HardwareBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHardwareBrandRepository extends JpaRepository<HardwareBrandEntity, Long> {
}
