package com.brainventory_mgmt.assets.models.hardware.hardwareType;

import com.brainventory_mgmt.assets.models.hardware.hardwareLine.HardwareLineEntity;
import com.brainventory_mgmt.assets.models.hardware.hardwareName.HardwareNameEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "hardware_types")
@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HardwareTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long idHardwareType;

    @Column(name = "type_name", nullable = false, unique = true)
    String typeName;

    @OneToMany(mappedBy = "hardwareType", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<HardwareNameEntity> hardwareNames;

    @OneToMany(mappedBy = "hardwareType", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<HardwareLineEntity> hardwareLines;
}
