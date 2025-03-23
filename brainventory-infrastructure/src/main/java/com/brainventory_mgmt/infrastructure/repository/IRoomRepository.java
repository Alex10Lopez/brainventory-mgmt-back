package com.brainventory_mgmt.infrastructure.repository;

import com.brainventory_mgmt.infrastructure.models.room.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomRepository extends JpaRepository<RoomEntity, Long> {
}
