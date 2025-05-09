package com.brainventory_mgmt.human_resources.controllers;

import com.brainventory_mgmt.human_resources.dto.employee.EmployeeDTO;
import com.brainventory_mgmt.human_resources.dto.employee.EmployeeListDTO;
import com.brainventory_mgmt.human_resources.dto.employee.EmployeeRequestDTO;
import com.brainventory_mgmt.human_resources.services.interfaces.IEmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeRestController {
    private final IEmployeeService employeeService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<EmployeeRequestDTO> saveEmployee(@RequestPart("employee") @Valid EmployeeRequestDTO employeeRequestDTO,
                                                           @RequestPart(value = "image", required = false) MultipartFile image){
        return new ResponseEntity<>(employeeService.saveEmployee(employeeRequestDTO, image), HttpStatus.CREATED);
    }

    @GetMapping("/all/{encodedEmail}")
    public ResponseEntity<List<EmployeeListDTO>> findAllEmployees(@PathVariable String encodedEmail) {
        try {
            String email = URLDecoder.decode(encodedEmail, StandardCharsets.UTF_8);
            List<EmployeeListDTO> employeeList = employeeService.findAllEmployees(email);

            return ResponseEntity.ok(employeeList);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<EmployeeRequestDTO> updateEmployee(@RequestPart("employee") @Valid EmployeeRequestDTO employeeRequestDTO,
                                                             @RequestPart(value = "image", required = false) MultipartFile image,
                                                             @PathVariable Long id){
        return new ResponseEntity<>(employeeService.updateEmployee(employeeRequestDTO, image, id), HttpStatus.OK);
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
