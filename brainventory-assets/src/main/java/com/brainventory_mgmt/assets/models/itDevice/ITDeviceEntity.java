package com.brainventory_mgmt.assets.models.itDevice;

import com.brainventory_mgmt.assets.models.hardware.hardwareDetails.HardwareDetailsEntity;
import com.brainventory_mgmt.assets.models.ioDevice.IODeviceEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "it_devices")
@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ITDeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long idITDevice;

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

    @Column(name = "id_room")
    Long idRoom;

    @OneToMany(mappedBy = "itDevice", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<IODeviceEntity> ioDevice;
}
