package com.hospital.management.service;

import com.hospital.management.entity.Patients;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PatientService {

   
    Patients createPatient(Patients patient);

    Patients updatePatient(Long id, Patients patient);

    void deletePatient(Long id);

    Optional<Patients> getPatientById(Long id);

    List<Patients> getAllPatients();
    
    List<Patients> getPatientsRegisteredOn(LocalDate date);

    Optional<Patients> getPatientByEmail(String email);

    Optional<Patients> getPatientByPhone(String phone);

    List<Patients> getPatientsByFirstName(String firstName);

    List<Patients> getPatientsByLastName(String lastName);

    List<Patients> getPatientsByFullName(String firstName, String lastName);

    List<Patients> getPatientsRegisteredAfter(LocalDateTime registrationDate);

    List<Patients> getPatientsRegisteredBefore(LocalDateTime registrationDate);

    List<Patients> getPatientsRegisteredBetween(LocalDateTime startDate, LocalDateTime endDate);
}
