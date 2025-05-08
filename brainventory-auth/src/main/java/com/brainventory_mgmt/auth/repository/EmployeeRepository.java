package com.brainventory_mgmt.auth.repository;

import com.brainventory_mgmt.auth.enums.EmployeePermissions;
import com.brainventory_mgmt.auth.models.EmployeeAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeAuthEntity, Long> {
    @Query("SELECT e FROM EmployeeAuthEntity e JOIN e.contacts c WHERE c.email = :email")
    Optional<EmployeeAuthEntity> findByContactsEmail(String email);

    @Modifying
    @Transactional
    @Query("""
        UPDATE EmployeeAuthEntity e
        SET e.loginDate = :loginDate
        WHERE EXISTS (
            SELECT c FROM ContactEntity c
            WHERE c.employee = e AND c.email = :email
        )
    """)
    void updateLoginDateByEmail(String email, LocalDateTime loginDate);

    boolean existsByPermissions(EmployeePermissions permissions);
}
