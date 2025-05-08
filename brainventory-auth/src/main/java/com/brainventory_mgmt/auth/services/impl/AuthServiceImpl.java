package com.brainventory_mgmt.auth.services.impl;

import com.brainventory_mgmt.auth.dto.AuthResponse;
import com.brainventory_mgmt.auth.dto.LoginRequest;
import com.brainventory_mgmt.auth.dto.RegisterRequest;
import com.brainventory_mgmt.auth.enums.EmployeePermissions;
import com.brainventory_mgmt.auth.enums.EmployeeStatus;
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
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

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

        employeeRepository.updateLoginDateByEmail(request.getEmail(), LocalDateTime.now());

        String token = jwtService.getToken(employee);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthResponse register(RegisterRequest request, MultipartFile image) {
        if (employeeRepository.existsByPermissions(EmployeePermissions.GLOBAL_ADMIN)) {
            throw new IllegalStateException("No se permite el registro. Ya existe un Administrador global.");
        }

        request.setPermissions(EmployeePermissions.GLOBAL_ADMIN);
        request.setStatus(EmployeeStatus.ACTIVE);

        try{
            EmployeeAuthEntity employeeAuthEntity = modelMapper.map(request, EmployeeAuthEntity.class);

            employeeAuthEntity.setPassword(passwordEncoder.encode(request.getPassword()));

            for (ContactEntity contact : employeeAuthEntity.getContacts())
                contact.setEmployee(employeeAuthEntity);

            if (image != null && !image.isEmpty()) {
                String folderPath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt/images/human-resources/employees/";
                String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path path = Paths.get(folderPath + filename);
                Files.createDirectories(path.getParent());
                Files.write(path, image.getBytes());
                employeeAuthEntity.setImage("/images/human-resources/employees/" + filename);
            }

            EmployeeAuthEntity savedEmployee = employeeRepository.save(employeeAuthEntity);

            //return AuthResponse.builder().token(jwtService.getToken(savedEmployee)).build();

            AuthResponse authResponse = modelMapper.map(savedEmployee, AuthResponse.class);
            authResponse.setToken(jwtService.getToken(savedEmployee));

            return authResponse;
        } catch (Exception e){
            //e.printStackTrace();
            throw new UnsupportedOperationException("Error saving the admin!");
        }
    }
}