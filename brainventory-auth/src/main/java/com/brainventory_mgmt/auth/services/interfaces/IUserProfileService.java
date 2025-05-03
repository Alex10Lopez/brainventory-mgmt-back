package com.brainventory_mgmt.auth.services.interfaces;

import com.brainventory_mgmt.auth.dto.profile.EmployeeDTO;
import org.springframework.stereotype.Repository;

public interface IUserProfileService {
    EmployeeDTO findByContactsEmail(String email);

    EmployeeDTO findById(Long id);
}
