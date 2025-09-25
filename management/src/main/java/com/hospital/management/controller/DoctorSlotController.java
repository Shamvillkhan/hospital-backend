package com.hospital.management.controller;

import com.hospital.management.entity.DoctorSlots;
import com.hospital.management.service.DoctorSlotService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/doctorslots")
public class DoctorSlotController {

    private final DoctorSlotService doctorSlotService;

    public DoctorSlotController(DoctorSlotService doctorSlotService) {
        this.doctorSlotService = doctorSlotService;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllSlots() {
        try {
            List<DoctorSlots> slots = doctorSlotService.getAllSlots();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "All slots fetched successfully");
            response.put("slots", slots);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Failed to fetch slots"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSlotById(@PathVariable long id) {
        try {
            Optional<DoctorSlots> slot = doctorSlotService.getSlotById(id);
            if (slot.isPresent()) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Slot found successfully");
                response.put("slot", slot.get());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Collections.singletonMap("message", "Slot not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Error fetching slot"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> createSlot(@RequestBody DoctorSlots doctorSlot) {
        try {
            DoctorSlots created = doctorSlotService.createSlot(doctorSlot);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Slot created successfully");
            response.put("slot", created);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Invalid input or slot creation failed"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Unexpected error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSlot(@PathVariable long id, @RequestBody DoctorSlots doctorSlot) {
        try {
            DoctorSlots updated = doctorSlotService.updateSlot(id, doctorSlot);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Slot updated successfully");
            response.put("slot", updated);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Slot not found or update failed"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Unexpected error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSlot(@PathVariable long id) {
        try {
            doctorSlotService.deleteSlot(id);
            return new ResponseEntity<>(Collections.singletonMap("message", "Slot deleted successfully"), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Collections.singletonMap("message", "Slot not found for deletion"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("error", "Unexpected error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
