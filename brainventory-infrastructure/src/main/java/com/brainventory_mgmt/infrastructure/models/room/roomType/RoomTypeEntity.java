package com.brainventory_mgmt.infrastructure.models.room.roomType;

import com.brainventory_mgmt.infrastructure.models.room.RoomEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "room_types")
@EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "type_name", nullable = false, unique = true)
    String typeName;

    @OneToMany(mappedBy = "roomType", fetch = FetchType.LAZY)
    List<RoomEntity> room;
}
