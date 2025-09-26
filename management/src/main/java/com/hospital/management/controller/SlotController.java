package com.hospital.management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hospital.management.entity.Slots;
import com.hospital.management.service.SlotService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/slots")
public class SlotController {

    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createSlot(@Valid @RequestBody Slots slot) {
        try {
            Slots created = slotService.createSlot(slot);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating slot: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllSlots() {
        try {
            List<Slots> slots = slotService.getAllSlots();
            return ResponseEntity.ok(slots);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching slots: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSlotById(@PathVariable long id) {
        try {
            Slots slot = slotService.getSlotById(id);
            return ResponseEntity.ok(slot);
        } catch (Exception e) {
            return new ResponseEntity<>("Slot not found: " + e.getMessage(),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSlot(@PathVariable long id, @RequestBody Slots slot) {
        try {
            Slots updated = slotService.updateSlot(id, slot);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating slot: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSlot(@PathVariable long id) {
        try {
            slotService.deleteSlot(id);
            return new ResponseEntity<>("Slot deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting slot: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
