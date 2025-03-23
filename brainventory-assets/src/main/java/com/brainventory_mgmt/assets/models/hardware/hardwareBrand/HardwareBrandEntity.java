package com.brainventory_mgmt.assets.models.hardware.hardwareBrand;

import com.brainventory_mgmt.assets.models.hardware.hardwareDetails.HardwareDetailsEntity;
import com.brainventory_mgmt.assets.models.hardware.hardwareLine.HardwareLineEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "hardware_brands")
@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HardwareBrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long idHardwareBrand;

    @Column(nullable = false, unique = true)
    String brand;

    @OneToMany(mappedBy = "hardwareBrand", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<HardwareLineEntity> hardwareLines;

    @OneToMany(mappedBy = "hardwareBrand", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<HardwareDetailsEntity> hardwareDetails;
}
