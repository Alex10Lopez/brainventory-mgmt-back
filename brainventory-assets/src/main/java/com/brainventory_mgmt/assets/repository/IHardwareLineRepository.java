package com.brainventory_mgmt.assets.repository;

import com.brainventory_mgmt.assets.models.hardware.hardwareLine.HardwareLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHardwareLineRepository extends JpaRepository<HardwareLineEntity, Long> {
    @Query("SELECT hn FROM HardwareLineEntity hn JOIN hn.hardwareType ht WHERE ht.typeName = 'it_devices'")
    List<HardwareLineEntity> findAllITDeviceLines();

    @Query("SELECT hn FROM HardwareLineEntity hn JOIN hn.hardwareType ht WHERE ht.typeName = 'io_devices'")
    List<HardwareLineEntity> findAllIODeviceLines();
}
