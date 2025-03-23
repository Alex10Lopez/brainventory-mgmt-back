package com.brainventory_mgmt.assets.repository;

import com.brainventory_mgmt.assets.models.hardware.hardwareDetails.HardwareDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHardwareDetailsRepository extends JpaRepository<HardwareDetailsEntity, Long> {
}
