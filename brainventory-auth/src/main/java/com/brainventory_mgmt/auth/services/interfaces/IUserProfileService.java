package com.brainventory_mgmt.auth.services.interfaces;

import com.brainventory_mgmt.auth.dto.profile.EmployeeDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserProfileService {
    EmployeeDTO getProfile(String email);
    EmployeeDTO updateProfile(EmployeeDTO employeeDTO, String email);
}
