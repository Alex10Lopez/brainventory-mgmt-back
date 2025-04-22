package com.brainventory_mgmt.assets.models.hardware.hardwareDetails;

import com.brainventory_mgmt.assets.enums.HardwareOperationalStatus;
import com.brainventory_mgmt.assets.enums.HardwarePhysicalStatus;
import com.brainventory_mgmt.assets.models.hardware.hardwareBrand.HardwareBrandEntity;
import com.brainventory_mgmt.assets.models.hardware.hardwareLine.HardwareLineEntity;
import com.brainventory_mgmt.assets.models.hardware.hardwareName.HardwareNameEntity;
import com.brainventory_mgmt.assets.models.hardware.hardwareSerie.HardwareSerieEntity;
import com.brainventory_mgmt.assets.models.ioDevice.IODeviceEntity;
import com.brainventory_mgmt.assets.models.itDevice.ITDeviceEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "hardware_details")
@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HardwareDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long idHardwareDetails;

    @Column(name = "serial_number", nullable = false, unique = true)
    String serialNumber;

    String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    HardwarePhysicalStatus physicalStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    HardwareOperationalStatus operationalStatus;

    @Column(name = "purchase_date", nullable = false)
    LocalDate purchaseDate;

    @Column(name = "warranty_end_date", nullable = false)
    LocalDate warrantyEndDate;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "id_hardware_name", nullable = false)
    HardwareNameEntity hardwareName;

    @ManyToOne
    @JoinColumn(name = "id_hardware_brand", nullable = false)
    HardwareBrandEntity hardwareBrand;

    @ManyToOne
    @JoinColumn(name = "id_hardware_line", nullable = false)
    HardwareLineEntity hardwareLine;

    @ManyToOne
    @JoinColumn(name = "id_hardware_serie", nullable = false)
    HardwareSerieEntity hardwareSerie;

    @OneToOne(mappedBy = "hardwareDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    ITDeviceEntity itDevice;

    @OneToOne(mappedBy = "hardwareDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    IODeviceEntity ioDevice;
}
