package com.hospital.management.controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.management.entity.HealthAppointment;
import com.hospital.management.service.HealthAppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/healthappointments")
public class HealthAppointmentController {

    private final HealthAppointmentService appointmentService;


    public HealthAppointmentController(HealthAppointmentService appointmentService) {
        this.appointmentService = appointmentService;

    }

    @PostMapping("/add")
    public ResponseEntity<HealthAppointment> createAppointment(@Valid @RequestBody HealthAppointment appointment) {

        HealthAppointment saved = appointmentService.createAppointment(appointment);
        return ResponseEntity.ok(saved);
    }

    // 👉 Get appointment by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<HealthAppointment> getAppointmentById(@PathVariable Long id) {
    	HealthAppointment appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    // 👉 Get all appointments
    @GetMapping("/getall")
    public ResponseEntity<List<HealthAppointment>> getAllAppointments() {
        List<HealthAppointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    // 👉 Update appointment
    @PutMapping("/update/{id}")
    public ResponseEntity<HealthAppointment> updateAppointment(@Valid
            @PathVariable Long id,
            @Valid @RequestBody HealthAppointment appointmentDetails) {

    	HealthAppointment updated = appointmentService.updateAppointment(id, appointmentDetails);
        return ResponseEntity.ok(updated);
    }

    // 👉 Delete appointment
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok("Appointment deleted successfully with id: " + id);
    }
    
    
    @GetMapping("/date/{date}")
    public ResponseEntity<List<HealthAppointment>> getAppointmentsByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date); // format: yyyy-MM-dd
        return ResponseEntity.ok(appointmentService.getAppointmentsByDate(localDate));
    }

    // ✅ Get appointments by status (Sorted by date ASC)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<HealthAppointment>> getAppointmentsByStatus(@PathVariable String status) {
    	HealthAppointment.AppointmentStatus appointmentStatus =
    			HealthAppointment.AppointmentStatus.valueOf(status.toUpperCase());
        return ResponseEntity.ok(appointmentService.getAppointmentsByStatus(appointmentStatus));
    }

    // ✅ Get appointments by date & status
    @GetMapping("/date/{date}/status/{status}")
    public ResponseEntity<List<HealthAppointment>> getAppointmentsByDateAndStatus(
            @PathVariable String date,
            @PathVariable String status) {
        LocalDate localDate = LocalDate.parse(date); // yyyy-MM-dd
        HealthAppointment.AppointmentStatus appointmentStatus =
        		HealthAppointment.AppointmentStatus.valueOf(status.toUpperCase());

        return ResponseEntity.ok(appointmentService.getAppointmentsByDateAndStatus(localDate, appointmentStatus));
    }
}
