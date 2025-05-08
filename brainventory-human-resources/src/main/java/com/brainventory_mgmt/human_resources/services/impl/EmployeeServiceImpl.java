package com.brainventory_mgmt.human_resources.services.impl;

import com.brainventory_mgmt.human_resources.dto.employee.EmployeeDTO;
import com.brainventory_mgmt.human_resources.dto.employee.EmployeeListDTO;
import com.brainventory_mgmt.human_resources.dto.employee.EmployeeRequestDTO;
import com.brainventory_mgmt.human_resources.models.employee.address.AddressEntity;
import com.brainventory_mgmt.human_resources.models.employee.contact.ContactEntity;
import com.brainventory_mgmt.human_resources.models.employee.EmployeeEntity;
import com.brainventory_mgmt.human_resources.repository.IEmployeeRepository;
import com.brainventory_mgmt.human_resources.services.interfaces.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {
    private final IEmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public EmployeeRequestDTO saveEmployee(EmployeeRequestDTO employeeCreateDTO, MultipartFile image) {
        try{
            EmployeeEntity employee = modelMapper.map(employeeCreateDTO, EmployeeEntity.class);

            employee.setPassword(passwordEncoder.encode(employeeCreateDTO.getPassword()));

            for (ContactEntity contact : employee.getContacts())
                contact.setEmployee(employee);

            for (AddressEntity address : employee.getAddresses())
                address.setEmployee(employee);

            if (image != null && !image.isEmpty()) {
                String folderPath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt/images/human-resources/employees/";
                String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path path = Paths.get(folderPath + filename);
                Files.createDirectories(path.getParent());
                Files.write(path, image.getBytes());
                employee.setImage("/images/human-resources/employees/" + filename);
            }

            EmployeeEntity savedEmployee = employeeRepository.save(employee);

            return modelMapper.map(savedEmployee, EmployeeRequestDTO.class);
        } catch (Exception e){
            //e.printStackTrace();
            throw new UnsupportedOperationException("Error saving the employee!");
        }
    }

    @Override
    public List<EmployeeListDTO> findAllEmployees(String email) {
        return employeeRepository.findAllEmployees(email)
                .stream()
                .map(employee -> {
                    EmployeeListDTO employeeDTO = modelMapper.map(employee, EmployeeListDTO.class);
                    if (employee.getContacts() != null && !employee.getContacts().isEmpty())
                        employeeDTO.setContacts(employeeDTO.getContacts().subList(0, 1));

                    if (employee.getJobRoles() != null && !employee.getJobRoles().isEmpty())
                        employeeDTO.setJobRoles(employeeDTO.getJobRoles().subList(0, 1));

                    return employeeDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO findById(Long id) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public EmployeeRequestDTO updateEmployee(EmployeeRequestDTO employeeRequestDTO, MultipartFile image, Long id) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        EmployeeEntity updatedEmployee = modelMapper.map(employeeRequestDTO, EmployeeEntity.class);
        updatedEmployee.setId(id);

        for (AddressEntity address : updatedEmployee.getAddresses())
            address.setEmployee(updatedEmployee);

        for (ContactEntity contact : updatedEmployee.getContacts())
            contact.setEmployee(updatedEmployee);

        if (employeeRequestDTO.getPassword() != null && !employeeRequestDTO.getPassword().isBlank()) {
            boolean isSamePassword = passwordEncoder.matches(employeeRequestDTO.getPassword(), existingEmployee.getPassword());
            if (!isSamePassword) {
                updatedEmployee.setPassword(passwordEncoder.encode(employeeRequestDTO.getPassword()));
            } else {
                updatedEmployee.setPassword(existingEmployee.getPassword());
            }
        } else {
            updatedEmployee.setPassword(existingEmployee.getPassword());
        }

        if (image != null && !image.isEmpty()) {
            if (existingEmployee.getImage() != null && !existingEmployee.getImage().isBlank()) {
                String basePath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt";
                Path oldImagePath = Paths.get(basePath + existingEmployee.getImage());
                try {
                    Files.deleteIfExists(oldImagePath);
                } catch (Exception e) {
                    throw new RuntimeException("Error deleting previous employee image: " + e.getMessage());
                }
            }

            String folderPath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt/images/human-resources/employees/";
            String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            Path path = Paths.get(folderPath + filename);
            try {
                Files.createDirectories(path.getParent());
                Files.write(path, image.getBytes());
                updatedEmployee.setImage("/images/human-resources/employees/" + filename);
            } catch (Exception e) {
                throw new RuntimeException("Error saving new image: " + e.getMessage());
            }
        } else {
            // Mantener imagen previa si no se actualiza
            updatedEmployee.setImage(existingEmployee.getImage());
        }

        EmployeeEntity savedEmployee = employeeRepository.save(updatedEmployee);
        return modelMapper.map(savedEmployee, EmployeeRequestDTO.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (employee.getImage() != null && !employee.getImage().isBlank()) {
            String basePath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt";
            Path imagePath = Paths.get(basePath + employee.getImage());
            try {
                Files.deleteIfExists(imagePath);
            } catch (Exception e) {
                throw new RuntimeException("Error deleting employee image: " + e.getMessage());
            }
        }

        employeeRepository.deleteById(id);
    }
}
