package com.brainventory_mgmt.auth.services.interfaces;

import com.brainventory_mgmt.auth.dto.AuthResponse;
import com.brainventory_mgmt.auth.dto.LoginRequest;
import com.brainventory_mgmt.auth.dto.RegisterRequest;
import org.springframework.web.multipart.MultipartFile;

public interface IAuthService {
    AuthResponse login(LoginRequest loginRequest);

    AuthResponse register(RegisterRequest registerRequest, MultipartFile image);
}
