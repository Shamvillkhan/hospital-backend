package com.hospital.management.controller;

import com.hospital.management.entity.Patients;
import com.hospital.management.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @PostMapping("/add")
    public ResponseEntity<Patients> createPatient(@RequestBody Patients patient) {
        Patients savedPatient = patientService.createPatient(patient);
        return ResponseEntity.ok(savedPatient);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Patients> updatePatient(@PathVariable Long id, @RequestBody Patients patient) {
        Patients updatedPatient = patientService.updatePatient(id, patient);
        return ResponseEntity.ok(updatedPatient);
    }

  
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully with ID: " + id);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Patients> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @GetMapping("/getall")
    public ResponseEntity<List<Patients>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<Patients> getPatientByEmail(@PathVariable String email) {
        return patientService.getPatientByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<Patients> getPatientByPhone(@PathVariable String phone) {
        return patientService.getPatientByPhone(phone)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/firstname/{firstName}")
    public ResponseEntity<List<Patients>> getPatientsByFirstName(@PathVariable String firstName) {
        return ResponseEntity.ok(patientService.getPatientsByFirstName(firstName));
    }


    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<Patients>> getPatientsByLastName(@PathVariable String lastName) {
        return ResponseEntity.ok(patientService.getPatientsByLastName(lastName));
    }

    // 🔹 Get by Full Name
    @GetMapping("/fullname")
    public ResponseEntity<List<Patients>> getPatientsByFullName(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        return ResponseEntity.ok(patientService.getPatientsByFullName(firstName, lastName));
    }

    @GetMapping("/registered-after/{dateTime}")
    public ResponseEntity<List<Patients>> getPatientsRegisteredAfter(@PathVariable String dateTime) {
        LocalDateTime parsedDate = LocalDateTime.parse(dateTime);
        return ResponseEntity.ok(patientService.getPatientsRegisteredAfter(parsedDate));
    }

    @GetMapping("/registered-before/{dateTime}")
    public ResponseEntity<List<Patients>> getPatientsRegisteredBefore(@PathVariable String dateTime) {
        LocalDateTime parsedDate = LocalDateTime.parse(dateTime);
        return ResponseEntity.ok(patientService.getPatientsRegisteredBefore(parsedDate));
    }
    
    @GetMapping("/registered-on")
    public ResponseEntity<List<Patients>> getPatientsByRegistrationDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Patients> patients = patientService.getPatientsRegisteredOn(date);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/registered-between")
    public ResponseEntity<List<Patients>> getPatientsRegisteredBetween(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        return ResponseEntity.ok(patientService.getPatientsRegisteredBetween(start, end));
    }
}
