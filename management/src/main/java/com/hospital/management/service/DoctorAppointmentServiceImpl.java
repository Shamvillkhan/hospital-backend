package com.hospital.management.service;

import com.hospital.management.entity.DoctorAppointment;
import com.hospital.management.repository.DoctorAppointmentRepository;
import com.hospital.management.service.DoctorAppointmentService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorAppointmentServiceImpl implements DoctorAppointmentService {

    private final DoctorAppointmentRepository appointmentRepository;

    public DoctorAppointmentServiceImpl(DoctorAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public DoctorAppointment createAppointment(DoctorAppointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public DoctorAppointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    @Override
    public List<DoctorAppointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public DoctorAppointment updateAppointment(Long id, DoctorAppointment appointmentDetails) {
        DoctorAppointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));

        existingAppointment.setName(appointmentDetails.getName());
        existingAppointment.setAppointmentDate(appointmentDetails.getAppointmentDate());
        existingAppointment.setAppointmentTime(appointmentDetails.getAppointmentTime());
        existingAppointment.setStatus(appointmentDetails.getStatus());
        existingAppointment.setNotes(appointmentDetails.getNotes());
        existingAppointment.setEmail(appointmentDetails.getEmail());
        existingAppointment.setPhone(appointmentDetails.getPhone());

        return appointmentRepository.save(existingAppointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        DoctorAppointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
        appointmentRepository.delete(existingAppointment);
    }
    
    
    @Override
    public List<DoctorAppointment> getAppointmentsByDate(LocalDate date) {
        return appointmentRepository.findByAppointmentDate(date);
    }

    @Override
    public List<DoctorAppointment> getAppointmentsByStatus(DoctorAppointment.AppointmentStatus status) {
        return appointmentRepository.findByStatusOrderByAppointmentDateAsc(status);
    }

    @Override
    public List<DoctorAppointment> getAppointmentsByDateAndStatus(LocalDate date, DoctorAppointment.AppointmentStatus status) {
        return appointmentRepository.findByAppointmentDateAndStatus(date, status);
    }
}
