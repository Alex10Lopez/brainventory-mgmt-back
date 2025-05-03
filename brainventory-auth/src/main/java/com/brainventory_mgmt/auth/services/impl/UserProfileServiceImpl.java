package com.brainventory_mgmt.auth.services.impl;

import com.brainventory_mgmt.auth.dto.AuthResponse;
import com.brainventory_mgmt.auth.dto.LoginRequest;
import com.brainventory_mgmt.auth.dto.profile.EmployeeDTO;
import com.brainventory_mgmt.auth.models.EmployeeAuthEntity;
import com.brainventory_mgmt.auth.repository.EmployeeRepository;
import com.brainventory_mgmt.auth.services.interfaces.IJwtService;
import com.brainventory_mgmt.auth.services.interfaces.IUserProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements IUserProfileService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public EmployeeDTO findByContactsEmail(String email) {
        EmployeeAuthEntity employee = employeeRepository.findByContactsEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO findById(Long id) {
        EmployeeAuthEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return modelMapper.map(employee, EmployeeDTO.class);
    }
}