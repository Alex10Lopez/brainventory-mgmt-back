package com.brainventory_mgmt.auth.repository;

import com.brainventory_mgmt.auth.models.EmployeeAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeAuthEntity, Long> {
    @Query("SELECT e FROM EmployeeAuthEntity e JOIN e.contacts c WHERE c.email = :email")
    Optional<EmployeeAuthEntity> findByContactsEmail(String email);
}
