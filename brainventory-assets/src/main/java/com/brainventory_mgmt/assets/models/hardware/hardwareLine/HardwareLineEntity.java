package com.brainventory_mgmt.assets.models.hardware.hardwareLine;

import com.brainventory_mgmt.assets.models.hardware.hardwareBrand.HardwareBrandEntity;
import com.brainventory_mgmt.assets.models.hardware.hardwareDetails.HardwareDetailsEntity;
import com.brainventory_mgmt.assets.models.hardware.hardwareSerie.HardwareSerieEntity;
import com.brainventory_mgmt.assets.models.hardware.hardwareType.HardwareTypeEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "hardware_lines")
@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HardwareLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long idHardwareLine;

    @Column(name = "line_name", nullable = false, unique = true)
    String lineName;

    @ManyToOne
    @JoinColumn(name = "id_hardware_brand", nullable = false)
    HardwareBrandEntity hardwareBrand;

    @OneToMany(mappedBy = "hardwareLine", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<HardwareSerieEntity> hardwareSerie;

    @ManyToOne
    @JoinColumn(name = "id_hardware_type", nullable = false)
    HardwareTypeEntity hardwareType;

    @OneToMany(mappedBy = "hardwareLine", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<HardwareDetailsEntity> hardwareDetails;
}
