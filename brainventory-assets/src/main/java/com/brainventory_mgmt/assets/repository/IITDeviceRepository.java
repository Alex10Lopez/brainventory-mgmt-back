package com.brainventory_mgmt.assets.repository;

import com.brainventory_mgmt.assets.models.itDevice.ITDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IITDeviceRepository extends JpaRepository<ITDeviceEntity, Long> {

}
