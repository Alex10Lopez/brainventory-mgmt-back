package com.brainventory_mgmt.infrastructure.repository;

import com.brainventory_mgmt.infrastructure.models.room.roomType.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomTypeRepository extends JpaRepository<RoomTypeEntity, Long> {
}
