package com.brainventory_mgmt.infrastructure.controllers;

import com.brainventory_mgmt.infrastructure.dto.department.DepartmentDTO;
import com.brainventory_mgmt.infrastructure.services.intefaces.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentRestController {
    private final IDepartmentService departmentService;

    @GetMapping("/references")
    public ResponseEntity<List<DepartmentDTO>> findAllDepartments(){
        return new ResponseEntity<>(departmentService.findAllDepartments(), HttpStatus.OK);
    }
}
