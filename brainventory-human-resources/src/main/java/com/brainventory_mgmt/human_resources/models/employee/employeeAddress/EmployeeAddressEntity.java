package com.brainventory_mgmt.human_resources.models.employee.employeeAddress;

import com.brainventory_mgmt.human_resources.models.employee.EmployeeEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "employees_address")
@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String street;

    @Column(name = "street_number", nullable = false)
    String streetNumber;

    @Column(name = "postal_code", nullable = false)
    String postalCode;

    @Column(nullable = false)
    String city;

    @Column(name = "country_state", nullable = false)
    String countryState;

    @Column(nullable = false)
    String country;

    String reference;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "id_employee", nullable = false)
    //@JsonBackReference
    EmployeeEntity employee;
}
