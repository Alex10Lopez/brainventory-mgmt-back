package com.brainventory_mgmt.assets.models.hardware.hardwareSerie;

import com.brainventory_mgmt.assets.models.hardware.hardwareDetails.HardwareDetailsEntity;
import com.brainventory_mgmt.assets.models.hardware.hardwareLine.HardwareLineEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "hardware_series")
@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HardwareSerieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long idHardwareSerie;

    @Column(nullable = false, unique = true)
    String serie;

    @ManyToOne
    @JoinColumn(name = "id_hardware_line", nullable = false)
    HardwareLineEntity hardwareLine;

    @OneToMany(mappedBy = "hardwareSerie", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<HardwareDetailsEntity> hardwareDetails;
}