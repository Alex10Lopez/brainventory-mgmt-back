package com.brainventory_mgmt.human_resources.repository;

import com.brainventory_mgmt.human_resources.models.employee.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Query("""
        SELECT e FROM EmployeeEntity e
        WHERE (
            (SELECT emp.permissions FROM EmployeeEntity emp 
             JOIN emp.contacts c 
             WHERE c.email = :email) = 'GLOBAL_ADMIN'
        )
        OR (
            (SELECT emp.permissions FROM EmployeeEntity emp 
             JOIN emp.contacts c 
             WHERE c.email = :email) = 'HR_ADMIN'
            AND (
                e.permissions NOT IN ('GLOBAL_ADMIN', 'HR_ADMIN') 
                OR e.id = (
                    SELECT emp.id FROM EmployeeEntity emp 
                    JOIN emp.contacts c 
                    WHERE c.email = :email
                )
            )
        )
    """)
    List<EmployeeEntity> findAllEmployees(String email);

    /*List<EmployeeEntity> findAll();
    Optional<EmployeeEntity> findById(Long id);
    EmployeeEntity save(EmployeeEntity employeeEntity);
    void  deleteById(Long id);*/
}
