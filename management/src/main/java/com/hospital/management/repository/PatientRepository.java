package com.hospital.management.repository;

import com.hospital.management.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patients, Long> {

	Optional<Patients> findByEmail(String email);

	Optional<Patients> findByPhone(String phone);

	List<Patients> findByFirstNameIgnoreCase(String firstName);

	List<Patients> findByLastNameIgnoreCase(String lastName);
	
	 @Query("SELECT p FROM Patients p WHERE p.registrationDate BETWEEN :startOfDay AND :endOfDay")
	    List<Patients> findAllByRegistrationDate(@Param("startOfDay") LocalDateTime startOfDay,
	                                             @Param("endOfDay") LocalDateTime endOfDay);

	List<Patients> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);

	List<Patients> findByRegistrationDateAfter(LocalDateTime registrationDate);

	List<Patients> findByRegistrationDateBefore(LocalDateTime registrationDate);

	List<Patients> findByRegistrationDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
