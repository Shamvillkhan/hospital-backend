package com.hospital.management.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hospital.management.entity.HealthAppointment;
import com.hospital.management.repository.HealthAppointmentRepository;

@Service
public class HealthAppointmentServiceImpl implements HealthAppointmentService {

    private final HealthAppointmentRepository appointmentRepository;

    public HealthAppointmentServiceImpl(HealthAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public HealthAppointment createAppointment(HealthAppointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public HealthAppointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    @Override
    public List<HealthAppointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public HealthAppointment updateAppointment(Long id, HealthAppointment appointmentDetails) {
    	HealthAppointment existingAppointment = appointmentRepository.findById(id)
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
    	HealthAppointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
        appointmentRepository.delete(existingAppointment);
    }
    
    
    @Override
    public List<HealthAppointment> getAppointmentsByDate(LocalDate date) {
        return appointmentRepository.findByAppointmentDate(date);
    }

    @Override
    public List<HealthAppointment> getAppointmentsByStatus(HealthAppointment.AppointmentStatus status) {
        return appointmentRepository.findByStatusOrderByAppointmentDateAsc(status);
    }

    @Override
    public List<HealthAppointment> getAppointmentsByDateAndStatus(LocalDate date, HealthAppointment.AppointmentStatus status) {
        return appointmentRepository.findByAppointmentDateAndStatus(date, status);
    }
}
