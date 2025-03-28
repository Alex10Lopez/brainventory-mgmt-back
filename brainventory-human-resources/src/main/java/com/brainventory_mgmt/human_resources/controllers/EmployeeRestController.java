package com.brainventory_mgmt.human_resources.controllers;

import com.brainventory_mgmt.human_resources.dto.employee.EmployeeDTO;
import com.brainventory_mgmt.human_resources.dto.employee.EmployeeListDTO;
import com.brainventory_mgmt.human_resources.dto.employee.EmployeeRequestDTO;
import com.brainventory_mgmt.human_resources.services.interfaces.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeRestController {
    private final IEmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeRequestDTO> saveEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO){
        return new ResponseEntity<>(employeeService.saveEmployee(employeeRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeListDTO>> findAll(){
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeRequestDTO> updateEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO, @PathVariable Long id){
        return new ResponseEntity<>(employeeService.updateEmployee(employeeRequestDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        try {
            employeeService.deleteEmployee(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
