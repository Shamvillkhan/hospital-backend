package com.hospital.management.controller;

import com.hospital.management.entity.Room;
import com.hospital.management.repository.RoomRepository;
import com.hospital.management.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    private final RoomRepository roomRepo;

    @Autowired
    public RoomController(RoomService roomService,RoomRepository roomRepo) {
        this.roomService = roomService;
        this.roomRepo=roomRepo;
    }
    
    
    @GetMapping("/count")
    public long countRooms() {
    	
        return roomRepo.count();
    }

    // ➕ Add Room
    @PostMapping("/add")
    public ResponseEntity<?> addRoom(@Valid @RequestBody Room room) {
        try {
            Room savedRoom = roomService.addRoom(room);
            return ResponseEntity.ok(savedRoom);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                "status", 400,
                "error", "Bad Request",
                "message", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                "status", 500,
                "error", "Internal Server Error",
                "message", e.getMessage()
            ));
        }
    }

    // ✏️ Update Room
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Long id, @Valid @RequestBody Room room) {
        try {
            Room updatedRoom = roomService.updateRoom(id, room);
            return ResponseEntity.ok(updatedRoom);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                "status", 400,
                "error", "Bad Request",
                "message", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                "status", 500,
                "error", "Internal Server Error",
                "message", e.getMessage()
            ));
        }
    }

    // ❌ Delete Room
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        try {
            roomService.deleteRoom(id);
            return ResponseEntity.ok(Map.of(
                "status", 200,
                "message", "Room deleted successfully"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                "status", 500,
                "error", "Internal Server Error",
                "message", e.getMessage()
            ));
        }
    }

    // 🔍 Get Room by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Long id) {
        try {
            Room room = roomService.getRoomById(id);
            return ResponseEntity.ok(room);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of(
                "status", 404,
                "error", "Not Found",
                "message", "Room with ID " + id + " not found"
            ));
        }
    }

    // 📋 Get All Rooms
    @GetMapping("/getall")
    public ResponseEntity<?> getAllRooms() {
        try {
            List<Room> rooms = roomService.getAllRooms();
            return ResponseEntity.ok(rooms);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                "status", 500,
                "error", "Internal Server Error",
                "message", e.getMessage()
            ));
        }
    }
}
