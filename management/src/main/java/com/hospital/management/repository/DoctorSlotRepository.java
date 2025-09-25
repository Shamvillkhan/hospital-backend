package com.hospital.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.management.entity.DoctorSlots;

@Repository
public interface DoctorSlotRepository extends JpaRepository<DoctorSlots, Long> {
	
	//Custom method will be create   for check all slots of doctor today or upcoming 
	//fetch by doctor name 
	//fetch by day 
	//fetch by date 
	//fetch by time 
	

}
