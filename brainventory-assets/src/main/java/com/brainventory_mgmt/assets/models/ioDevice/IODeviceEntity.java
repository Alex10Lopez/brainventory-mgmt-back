package com.brainventory_mgmt.assets.models.ioDevice;


import com.brainventory_mgmt.assets.models.hardware.hardwareDetails.HardwareDetailsEntity;
import com.brainventory_mgmt.assets.models.itDevice.ITDeviceEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "io_devices")
@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IODeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String image;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "id_hardware_details", nullable = false)
    HardwareDetailsEntity hardwareDetails;

    @ManyToOne
    @JoinColumn(name = "id_it_device")
    ITDeviceEntity itDevice;
}
