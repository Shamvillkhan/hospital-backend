package com.hospital.management.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.management.entity.Patients;
import com.hospital.management.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patients createPatient(Patients patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patients updatePatient(Long id, Patients patient) {
        Patients existing = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        existing.setFirstName(patient.getFirstName());
        existing.setLastName(patient.getLastName());
        existing.setDateOfBirth(patient.getDateOfBirth());
        existing.setGender(patient.getGender());
        existing.setPhone(patient.getPhone());
        existing.setAddress(patient.getAddress());
        existing.setEmail(patient.getEmail());
        existing.setBloodType(patient.getBloodType());
        return patientRepository.save(existing);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public Optional<Patients> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public List<Patients> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Optional<Patients> getPatientByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    @Override
    public Optional<Patients> getPatientByPhone(String phone) {
        return patientRepository.findByPhone(phone);
    }

    @Override
    public List<Patients> getPatientsByFirstName(String firstName) {
        return patientRepository.findByFirstNameIgnoreCase(firstName);
    }

    @Override
    public List<Patients> getPatientsByLastName(String lastName) {
        return patientRepository.findByLastNameIgnoreCase(lastName);
    }

    @Override
    public List<Patients> getPatientsByFullName(String firstName, String lastName) {
        return patientRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);
    }

    @Override
    public List<Patients> getPatientsRegisteredAfter(LocalDateTime registrationDate) {
        return patientRepository.findByRegistrationDateAfter(registrationDate);
    }

    @Override
    public List<Patients> getPatientsRegisteredBefore(LocalDateTime registrationDate) {
        return patientRepository.findByRegistrationDateBefore(registrationDate);
    }

    @Override
    public List<Patients> getPatientsRegisteredBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return patientRepository.findByRegistrationDateBetween(startDate, endDate);
    }
    
    
    @Override
    public List<Patients> getPatientsRegisteredOn(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        return patientRepository.findAllByRegistrationDate(startOfDay, endOfDay);
    }
}
