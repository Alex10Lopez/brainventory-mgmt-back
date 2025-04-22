package com.brainventory_mgmt.human_resources.models.employee;

import com.brainventory_mgmt.human_resources.enums.EmployeeSex;
import com.brainventory_mgmt.human_resources.enums.EmployeeStatus;
import com.brainventory_mgmt.human_resources.enums.EmployeePermissions;
import com.brainventory_mgmt.human_resources.models.employee.address.AddressEntity;
import com.brainventory_mgmt.human_resources.models.employee.contact.ContactEntity;
import com.brainventory_mgmt.human_resources.models.employee.jobRoles.JobRoleEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employees")
@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String image;

    @Column(nullable = false)
    String name;

    @Column(name = "last_name", nullable = false)
    String lastname;

    @Column(name = "date_of_birth", nullable = false)
    LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    EmployeeSex sex;

    @Column(nullable = false)
    String nationality;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    EmployeePermissions permissions;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_status", nullable = false)
    EmployeeStatus status;

    String password;

    @Column(nullable = false)
    BigDecimal salary;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;

    @Column(name = "login_date")
    LocalDateTime loginDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employee_roles", joinColumns = @JoinColumn(name = "id_employee"), inverseJoinColumns = @JoinColumn(name = "id_job_role"))
    List<JobRoleEntity> jobRoles;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference
    List<ContactEntity> contacts;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference
    List<AddressEntity> addresses;
}
