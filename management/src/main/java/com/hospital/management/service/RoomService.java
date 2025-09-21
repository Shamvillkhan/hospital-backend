package com.hospital.management.service;

import com.hospital.management.entity.Room;
import java.util.List;

public interface RoomService {
    Room addRoom(Room room);
    Room updateRoom(Long id, Room room);
    void deleteRoom(Long id);
    Room getRoomById(Long id);
    List<Room> getAllRooms();
}
