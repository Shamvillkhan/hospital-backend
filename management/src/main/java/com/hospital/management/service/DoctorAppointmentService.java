package com.hospital.management.service;

import com.hospital.management.entity.DoctorAppointment;

import java.time.LocalDate;
import java.util.List;

public interface DoctorAppointmentService {

    DoctorAppointment createAppointment(DoctorAppointment appointment);

    DoctorAppointment getAppointmentById(Long id);

    List<DoctorAppointment> getAllAppointments();

    DoctorAppointment updateAppointment(Long id, DoctorAppointment appointmentDetails);

    void deleteAppointment(Long id);
    
    List<DoctorAppointment> getAppointmentsByDate(LocalDate date);
    List<DoctorAppointment> getAppointmentsByStatus(DoctorAppointment.AppointmentStatus status);
    List<DoctorAppointment> getAppointmentsByDateAndStatus(LocalDate date, DoctorAppointment.AppointmentStatus status);
}
