package com.brainventory_mgmt.auth.controllers;

import com.brainventory_mgmt.auth.dto.AuthResponse;
import com.brainventory_mgmt.auth.dto.LoginRequest;
import com.brainventory_mgmt.auth.dto.RegisterRequest;
import com.brainventory_mgmt.auth.services.interfaces.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        //return ResponseEntity.ok(authService.login(loginRequest));
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
        //return new ResponseEntity<>(authService.register(registerRequest), HttpStatus.OK);
    }
}
