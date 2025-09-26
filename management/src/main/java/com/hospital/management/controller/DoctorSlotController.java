package com.hospital.management.controller;

import com.hospital.management.entity.DoctorSlots;
import com.hospital.management.repository.DoctorSlotRepository;
import com.hospital.management.service.DoctorSlotService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/doctorslots")
public class DoctorSlotController {

    private final DoctorSlotService doctorSlotService;
    private final DoctorSlotRepository doctorSlotsRepository;

    public DoctorSlotController(DoctorSlotService doctorSlotService,DoctorSlotRepository doctorSlotsRepository) {
        this.doctorSlotService = doctorSlotService;
        this.doctorSlotsRepository=doctorSlotsRepository;
    }
    
    
    @GetMapping("/byDoctor/{staffId}")
    public List<DoctorSlots> getDoctorSlotsByDoctor(@PathVariable long staffId) {
        return doctorSlotsRepository.findByStaffId_StaffId(staffId);
    }

    
    
    
    @GetMapping("/getall")
    public ResponseEntity<List<DoctorSlots>> getAllSlots() {
        try {
            List<DoctorSlots> slots = doctorSlotService.getAllSlots();
            return new ResponseEntity<>(slots, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public DoctorSlots getSlotById(@PathVariable long id) {
        return doctorSlotService.getSlotById(id)
                .orElseThrow(() -> new RuntimeException("Slot not found with id: " + id));
    }


    @PostMapping("/add")
    public ResponseEntity<DoctorSlots> createSlot(@RequestBody DoctorSlots doctorSlot) {
        DoctorSlots created = doctorSlotService.createSlot(doctorSlot);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DoctorSlots> updateSlot(@PathVariable long id, @RequestBody DoctorSlots doctorSlot) {
        DoctorSlots updated = doctorSlotService.updateSlot(id, doctorSlot);
        return ResponseEntity.ok(updated); // 200 OK ke saath direct object return
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
