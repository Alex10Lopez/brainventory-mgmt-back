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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {
    private final IEmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public EmployeeRequestDTO saveEmployee(EmployeeRequestDTO employeeCreateDTO) {
        try{
            EmployeeEntity employee = modelMapper.map(employeeCreateDTO, EmployeeEntity.class);

            employee.setPassword(passwordEncoder.encode(employeeCreateDTO.getPassword()));

            for (ContactEntity contact : employee.getContacts())
                contact.setEmployee(employee);

            for (AddressEntity address : employee.getAddresses())
                address.setEmployee(employee);

            EmployeeEntity savedEmployee = employeeRepository.save(employee);

            return modelMapper.map(savedEmployee, EmployeeRequestDTO.class);
        } catch (Exception e){
            //e.printStackTrace();
            throw new UnsupportedOperationException("Error saving the employee!");
        }
    }

    @Override
    public List<EmployeeListDTO> findAll() {
        return employeeRepository.findAll()
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
    public EmployeeRequestDTO updateEmployee(EmployeeRequestDTO employeeRequestDTO, Long id) {
        EmployeeEntity existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        EmployeeEntity updatedEmployee = modelMapper.map(employeeRequestDTO, EmployeeEntity.class);
        updatedEmployee.setId(id);

        for (AddressEntity address : updatedEmployee.getAddresses())
            address.setEmployee(updatedEmployee);

        for (ContactEntity contact : updatedEmployee.getContacts())
            contact.setEmployee(updatedEmployee);

        if (employeeRequestDTO.getPassword() != null && !employeeRequestDTO.getPassword().isBlank()) {
            updatedEmployee.setPassword(passwordEncoder.encode(employeeRequestDTO.getPassword()));
        }

        EmployeeEntity savedEmployee = employeeRepository.save(updatedEmployee);
        return modelMapper.map(savedEmployee, EmployeeRequestDTO.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id))
            throw new RuntimeException("Employee not found");

        employeeRepository.deleteById(id);
    }
}
