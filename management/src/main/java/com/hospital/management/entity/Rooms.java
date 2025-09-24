package com.hospital.management.entity;

import com.hospital.management.enums.RoomsStatus;
import com.hospital.management.enums.RoomsType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private long roomId;

    @Column(name = "room_number",length = 20)
    private String roomNo;

    @Column(name = "type",nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomsType type;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomsStatus status;
}
