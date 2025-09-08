package com.hospital.management.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hospital.management.entity.DoctorAppointment;

public interface DoctorAppointmentRepository extends JpaRepository<DoctorAppointment, Long> {
	
	List<DoctorAppointment> findByAppointmentDate(LocalDate date);
	List<DoctorAppointment> findByStatusOrderByAppointmentDateAsc(DoctorAppointment.AppointmentStatus status);
	List<DoctorAppointment> findByAppointmentDateAndStatus(LocalDate date, DoctorAppointment.AppointmentStatus status);

}
