package com.hospital.management.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hospital.management.entity.DoctorAppointment;
import com.hospital.management.entity.DoctorSlots;
import com.hospital.management.repository.DoctorAppointmentRepository;
import com.hospital.management.repository.DoctorSlotRepository;

@Service
public class DoctorAppointmentServiceImpl implements DoctorAppointmentService {

    private final DoctorAppointmentRepository appointmentRepository;
    private final DoctorSlotRepository doctorSlotRepository;

    public DoctorAppointmentServiceImpl(DoctorAppointmentRepository appointmentRepository,DoctorSlotRepository doctorSlotRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorSlotRepository=doctorSlotRepository;
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
