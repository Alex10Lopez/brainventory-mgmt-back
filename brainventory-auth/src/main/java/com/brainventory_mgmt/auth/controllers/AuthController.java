package com.brainventory_mgmt.auth.controllers;

import com.brainventory_mgmt.auth.dto.AuthResponse;
import com.brainventory_mgmt.auth.dto.LoginRequest;
import com.brainventory_mgmt.auth.dto.RegisterRequest;
import com.brainventory_mgmt.auth.services.interfaces.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/register", consumes = {"multipart/form-data"})
    public ResponseEntity<AuthResponse> register(@RequestPart("admin") RegisterRequest registerRequest,
                                                 @RequestPart(value = "image", required = false) MultipartFile image){
        return ResponseEntity.ok(authService.register(registerRequest, image));
        //return new ResponseEntity<>(authService.register(registerRequest), HttpStatus.OK);
    }
}