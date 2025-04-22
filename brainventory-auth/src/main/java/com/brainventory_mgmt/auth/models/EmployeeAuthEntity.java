package com.brainventory_mgmt.auth.models;

import com.brainventory_mgmt.auth.enums.EmployeePermissions;
import com.brainventory_mgmt.auth.enums.EmployeeSex;
import com.brainventory_mgmt.auth.enums.EmployeeStatus;
import com.brainventory_mgmt.auth.models.address.AddressEntity;
import com.brainventory_mgmt.auth.models.contact.ContactEntity;
import com.brainventory_mgmt.auth.models.jobRoles.JobRoleEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
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
public class EmployeeAuthEntity implements UserDetails {
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

    @Column(nullable = false)
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_roles", joinColumns = @JoinColumn(name = "id_employee"), inverseJoinColumns = @JoinColumn(name = "id_job_role"))
    List<JobRoleEntity> jobRoles;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    List<ContactEntity> contacts;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    List<AddressEntity> addresses;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((permissions.name())));
    }

    public String getEmail() {
        if (contacts != null && !contacts.isEmpty()) {
            return contacts.get(0).getEmail();
        }
        return null;
    }

    @Override
    public String getUsername() {
        if (contacts != null && !contacts.isEmpty())
            return contacts.get(0).getEmail();

        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}