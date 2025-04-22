package com.brainventory_mgmt.auth.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {
    String getToken(UserDetails employee);

    String getUsernameFromToken(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}