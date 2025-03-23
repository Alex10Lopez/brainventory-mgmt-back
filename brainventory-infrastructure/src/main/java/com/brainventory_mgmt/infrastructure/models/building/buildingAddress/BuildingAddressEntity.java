package com.brainventory_mgmt.infrastructure.models.building.buildingAddress;

import com.brainventory_mgmt.infrastructure.models.building.BuildingEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "buildings_address")
@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuildingAddressEntity {
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

    @OneToOne
    @JoinColumn(name = "id_building", nullable = false, unique = true)
    BuildingEntity building;
}
