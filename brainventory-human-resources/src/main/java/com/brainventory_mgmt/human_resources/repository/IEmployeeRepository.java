package com.brainventory_mgmt.human_resources.repository;

import com.brainventory_mgmt.human_resources.models.employee.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    /*List<EmployeeEntity> findAll();
    Optional<EmployeeEntity> findById(Long id);
    EmployeeEntity save(EmployeeEntity employeeEntity);
    void  deleteById(Long id);*/
}
