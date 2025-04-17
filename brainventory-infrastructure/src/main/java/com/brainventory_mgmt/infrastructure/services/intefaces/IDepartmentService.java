package com.brainventory_mgmt.infrastructure.services.intefaces;


import com.brainventory_mgmt.infrastructure.dto.department.DepartmentDTO;

import java.util.List;

public interface IDepartmentService {
    List<DepartmentDTO> findAllDepartments();
}
