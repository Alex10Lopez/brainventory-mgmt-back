package com.brainventory_mgmt.auth.services.impl;

import com.brainventory_mgmt.auth.dto.AuthResponse;
import com.brainventory_mgmt.auth.dto.LoginRequest;
import com.brainventory_mgmt.auth.repository.EmployeeRepository;
import com.brainventory_mgmt.auth.services.interfaces.IJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl {
    private final EmployeeRepository employeeRepository;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse updateProfile(LoginRequest request){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails employee = employeeRepository.findByContactsEmail(request.getEmail()).orElseThrow();

        String token = jwtService.getToken(employee);

        return AuthResponse.builder()
                .token(token)
                .build();
    }
}