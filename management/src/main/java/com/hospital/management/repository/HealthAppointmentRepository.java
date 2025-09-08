package com.hospital.management.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.management.entity.HealthAppointment;

public interface HealthAppointmentRepository extends JpaRepository<HealthAppointment, Long> {

	List<HealthAppointment> findByAppointmentDate(LocalDate date);

	List<HealthAppointment> findByStatusOrderByAppointmentDateAsc(HealthAppointment.AppointmentStatus status);

	List<HealthAppointment> findByAppointmentDateAndStatus(LocalDate date, HealthAppointment.AppointmentStatus status);

}
