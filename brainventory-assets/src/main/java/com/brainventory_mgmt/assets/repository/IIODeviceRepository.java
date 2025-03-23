package com.brainventory_mgmt.assets.repository;

import com.brainventory_mgmt.assets.models.ioDevice.IODeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIODeviceRepository extends JpaRepository<IODeviceEntity, Long> {
}
