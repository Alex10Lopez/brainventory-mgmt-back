package com.brainventory_mgmt.auth.controllers;

import com.brainventory_mgmt.auth.dto.profile.EmployeeDTO;
import com.brainventory_mgmt.auth.services.interfaces.IUserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserProfileRestController {
    private final IUserProfileService userProfileService;

    @GetMapping("/{encodedEmail}")
    public ResponseEntity<EmployeeDTO> findByContactsEmail(
            @PathVariable String encodedEmail) {

        try {
            String email = URLDecoder.decode(encodedEmail, StandardCharsets.UTF_8);
            EmployeeDTO employee = userProfileService.findByContactsEmail(email);
            return ResponseEntity.ok(employee);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
