package com.hospital.management.controller;

import com.hospital.management.entity.DoctorAppointment;
import com.hospital.management.entity.Staff;
import com.hospital.management.repository.StaffRepository;
import com.hospital.management.service.DoctorAppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/doctorappointments")
public class DoctorAppointmentController {

    private final DoctorAppointmentService appointmentService;
    private StaffRepository staffRepository;

    public DoctorAppointmentController(DoctorAppointmentService appointmentService,StaffRepository staffRepository) {
        this.appointmentService = appointmentService;
        this.staffRepository=staffRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<DoctorAppointment> createAppointment(@Valid @RequestBody DoctorAppointment appointment) {

        Staff staff = staffRepository.findById(appointment.getStaff().getStaffId())
                         .orElseThrow(() -> new RuntimeException("Staff not found"));
        appointment.setStaff(staff);

        DoctorAppointment saved = appointmentService.createAppointment(appointment);
        return ResponseEntity.ok(saved);
    }

    // 👉 Get appointment by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<DoctorAppointment> getAppointmentById(@PathVariable Long id) {
        DoctorAppointment appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    // 👉 Get all appointments
    @GetMapping("/getall")
    public ResponseEntity<List<DoctorAppointment>> getAllAppointments() {
        List<DoctorAppointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    // 👉 Update appointment
    @PutMapping("/update/{id}")
    public ResponseEntity<DoctorAppointment> updateAppointment(
            @PathVariable Long id,
            @Valid @RequestBody DoctorAppointment appointmentDetails) {

        DoctorAppointment updated = appointmentService.updateAppointment(id, appointmentDetails);
        return ResponseEntity.ok(updated);
    }

    // 👉 Delete appointment
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok("Appointment deleted successfully with id: " + id);
    }
    
    
    @GetMapping("/date/{date}")
    public ResponseEntity<List<DoctorAppointment>> getAppointmentsByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date); // format: yyyy-MM-dd
        return ResponseEntity.ok(appointmentService.getAppointmentsByDate(localDate));
    }

    // ✅ Get appointments by status (Sorted by date ASC)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<DoctorAppointment>> getAppointmentsByStatus(@PathVariable String status) {
        DoctorAppointment.AppointmentStatus appointmentStatus =
                DoctorAppointment.AppointmentStatus.valueOf(status.toUpperCase());
        return ResponseEntity.ok(appointmentService.getAppointmentsByStatus(appointmentStatus));
    }

    // ✅ Get appointments by date & status
    @GetMapping("/date/{date}/status/{status}")
    public ResponseEntity<List<DoctorAppointment>> getAppointmentsByDateAndStatus(
            @PathVariable String date,
            @PathVariable String status) {
        LocalDate localDate = LocalDate.parse(date); // yyyy-MM-dd
        DoctorAppointment.AppointmentStatus appointmentStatus =
                DoctorAppointment.AppointmentStatus.valueOf(status.toUpperCase());

        return ResponseEntity.ok(appointmentService.getAppointmentsByDateAndStatus(localDate, appointmentStatus));
    }
}
