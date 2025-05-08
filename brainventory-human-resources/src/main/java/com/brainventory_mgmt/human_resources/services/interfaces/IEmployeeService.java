package com.brainventory_mgmt.human_resources.services.interfaces;

import com.brainventory_mgmt.human_resources.dto.employee.EmployeeDTO;
import com.brainventory_mgmt.human_resources.dto.employee.EmployeeListDTO;
import com.brainventory_mgmt.human_resources.dto.employee.EmployeeRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEmployeeService {
    EmployeeRequestDTO saveEmployee(EmployeeRequestDTO employeeCreateDTO, MultipartFile image);
    List<EmployeeListDTO> findAllEmployees(String email);
    //List<EmployeeListDTO> findAll();
    EmployeeDTO findById(Long id);
    EmployeeRequestDTO updateEmployee(EmployeeRequestDTO employeeRequestDTO, MultipartFile image, Long id);
    void deleteEmployee(Long id);
}
