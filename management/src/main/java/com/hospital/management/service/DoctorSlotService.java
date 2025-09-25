package com.hospital.management.service;

import java.util.List;
import java.util.Optional;

import com.hospital.management.entity.DoctorSlots;

public interface DoctorSlotService {
	
	Optional<DoctorSlots> getSlotById(long id);
	void deleteSlot(long id);
	List<DoctorSlots> getAllSlots();
	DoctorSlots updateSlot(long id,DoctorSlots doctorSlot);
	DoctorSlots createSlot(DoctorSlots doctorSlot);
}
