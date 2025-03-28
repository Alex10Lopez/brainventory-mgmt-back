package com.brainventory_mgmt.infrastructure.models.room;

import com.brainventory_mgmt.infrastructure.models.building.BuildingEntity;
import com.brainventory_mgmt.infrastructure.models.department.DepartmentEntity;
import com.brainventory_mgmt.infrastructure.models.room.roomType.RoomTypeEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "rooms")
@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    String image;

    @Column(nullable = false)
    String name;

    @Column(name = "capacity_max")
    Integer capacityMax;

    @Column(name = "floor_label", nullable = false)
    String floorLabel;

    String description;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "id_room_type", nullable = false)
    RoomTypeEntity roomType;

    @ManyToOne
    @JoinColumn(name = "id_building", nullable = false)
    BuildingEntity building;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "room_departments", joinColumns = @JoinColumn(name = "id_room"), inverseJoinColumns = @JoinColumn(name = "id_department"))
    List<DepartmentEntity> departments;
}
