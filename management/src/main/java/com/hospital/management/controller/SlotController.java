package com.hospital.management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> createSlot(@Valid @RequestBody Slots slot) {
        Map<String, Object> response = new HashMap<>();
        try {
            Slots created = slotService.createSlot(slot);
            response.put("status", "success");
            response.put("message", "Slot created successfully");
            response.put("data", created);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<Map<String, Object>> getAllSlots() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Slots> slots = slotService.getAllSlots();
            response.put("status", "success");
            response.put("count", slots.size());
            response.put("data", slots);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Map<String, Object>> getSlotById(@PathVariable long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Slots slot = slotService.getSlotById(id);
            response.put("status", "success");
            response.put("data", slot);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateSlot(@PathVariable long id, @RequestBody Slots slot) {
        Map<String, Object> response = new HashMap<>();
        try {
            Slots updated = slotService.updateSlot(id, slot);
            response.put("status", "success");
            response.put("message", "Slot updated successfully");
            response.put("data", updated);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteSlot(@PathVariable long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            slotService.deleteSlot(id);
            response.put("status", "success");
            response.put("message", "Slot deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
