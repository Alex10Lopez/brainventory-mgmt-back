package com.brainventory_mgmt.assets.repository;

import com.brainventory_mgmt.assets.models.hardware.hardwareName.HardwareNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHardwareNameRepository extends JpaRepository<HardwareNameEntity, Long> {
    @Query("SELECT hn FROM HardwareNameEntity hn JOIN hn.hardwareType ht WHERE ht.typeName = 'it_devices'")
    List<HardwareNameEntity> findAllITDeviceNames();

    @Query("SELECT hn FROM HardwareNameEntity hn JOIN hn.hardwareType ht WHERE ht.typeName = 'io_devices'")
    List<HardwareNameEntity> findAllIODeviceNames();
}
