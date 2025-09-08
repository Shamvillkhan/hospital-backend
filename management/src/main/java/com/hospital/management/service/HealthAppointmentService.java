package com.hospital.management.service;

import java.time.LocalDate;
import java.util.List;

import com.hospital.management.entity.HealthAppointment;

public interface HealthAppointmentService {

	HealthAppointment createAppointment(HealthAppointment appointment);

	HealthAppointment getAppointmentById(Long id);

    List<HealthAppointment> getAllAppointments();

    HealthAppointment updateAppointment(Long id, HealthAppointment appointmentDetails);

    void deleteAppointment(Long id);
    
    List<HealthAppointment> getAppointmentsByDate(LocalDate date);
    List<HealthAppointment> getAppointmentsByStatus(HealthAppointment.AppointmentStatus status);
    List<HealthAppointment> getAppointmentsByDateAndStatus(LocalDate date, HealthAppointment.AppointmentStatus status);
}
