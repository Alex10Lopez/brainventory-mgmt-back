package com.brainventory_mgmt.infrastructure.services.intefaces;

import com.brainventory_mgmt.infrastructure.dto.department.DepartmentReferenceDTO;
import com.brainventory_mgmt.infrastructure.dto.room.roomType.RoomTypeDTO;

import java.util.List;

public interface IRoomTypeService {
    List<RoomTypeDTO> findAllReferences();
}
