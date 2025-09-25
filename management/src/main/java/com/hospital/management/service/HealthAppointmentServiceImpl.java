package com.hospital.management.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hospital.management.entity.DoctorSlots;
import com.hospital.management.entity.HealthAppointment;
import com.hospital.management.repository.DoctorSlotRepository;
import com.hospital.management.repository.HealthAppointmentRepository;

@Service
public class HealthAppointmentServiceImpl implements HealthAppointmentService {

    private final HealthAppointmentRepository appointmentRepository;
    private final DoctorSlotRepository doctorSlotRepository;

    public HealthAppointmentServiceImpl(HealthAppointmentRepository appointmentRepository, DoctorSlotRepository doctorSlotRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorSlotRepository=doctorSlotRepository;
    }

    @Override
    public HealthAppointment createAppointment(HealthAppointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public HealthAppointment getAppointmentById(long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    @Override
    public List<HealthAppointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public HealthAppointment updateAppointment(long id, HealthAppointment appointmentDetails) {
    	HealthAppointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    	
        DoctorSlots availableSlot = doctorSlotRepository.findById(appointmentDetails.getDoctorSlot().getDoctorSlotId())
                .orElseThrow(() -> new RuntimeException("Slot not found with id: " + appointmentDetails.getDoctorSlot().getSlotId()));

 
        if (!availableSlot.getIsActive()) {
            existingAppointment.setDoctorSlot(availableSlot);

           
            availableSlot.setIsActive(true);
            doctorSlotRepository.save(availableSlot);
        } else {
            throw new RuntimeException("Selected slot is already booked!");
        }

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
    public void deleteAppointment(long id) {
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
