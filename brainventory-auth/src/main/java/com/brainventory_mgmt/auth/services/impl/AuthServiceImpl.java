package com.brainventory_mgmt.auth.services.impl;

import com.brainventory_mgmt.auth.dto.AuthResponse;
import com.brainventory_mgmt.auth.dto.LoginRequest;
import com.brainventory_mgmt.auth.dto.RegisterRequest;
import com.brainventory_mgmt.auth.models.EmployeeAuthEntity;
import com.brainventory_mgmt.auth.models.contact.ContactEntity;
import com.brainventory_mgmt.auth.repository.EmployeeRepository;
import com.brainventory_mgmt.auth.services.interfaces.IAuthService;
import com.brainventory_mgmt.auth.services.interfaces.IJwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final IJwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(LoginRequest request) {
        //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        //UserDetails employee = employeeRepository.findByEmail(request.getEmail()).orElseThrow();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails employee = employeeRepository.findByContactsEmail(request.getEmail()).orElseThrow();

        String token = jwtService.getToken(employee);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        EmployeeAuthEntity employeeAuthEntity = modelMapper.map(request, EmployeeAuthEntity.class);

        employeeAuthEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        for (ContactEntity contact : employeeAuthEntity.getContacts())
            contact.setEmployee(employeeAuthEntity);

        EmployeeAuthEntity savedEmployee = employeeRepository.save(employeeAuthEntity);

        //return AuthResponse.builder().token(jwtService.getToken(savedEmployee)).build();

        AuthResponse authResponse = modelMapper.map(savedEmployee, AuthResponse.class);
        authResponse.setToken(jwtService.getToken(savedEmployee));

        return authResponse;
    }
}
