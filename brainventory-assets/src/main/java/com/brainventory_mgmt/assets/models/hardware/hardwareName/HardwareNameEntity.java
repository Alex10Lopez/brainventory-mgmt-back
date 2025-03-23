package com.brainventory_mgmt.assets.models.hardware.hardwareName;

import com.brainventory_mgmt.assets.models.hardware.hardwareDetails.HardwareDetailsEntity;
import com.brainventory_mgmt.assets.models.hardware.hardwareType.HardwareTypeEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "hardware_names")
@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HardwareNameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long idHardwareName;

    @Column(nullable = false, unique = true)
    String name;

    @ManyToOne
    @JoinColumn(name = "id_hardware_type", nullable = false)
    HardwareTypeEntity hardwareType;

    @OneToMany(mappedBy = "hardwareName", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<HardwareDetailsEntity> hardwareDetails;
}
