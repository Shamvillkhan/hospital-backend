package com.hospital.management.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hospital.management.entity.DoctorSlots;
import com.hospital.management.repository.DoctorSlotRepository;

@Service
public class DoctorSlotServiceImpl implements DoctorSlotService {
	
	private DoctorSlotRepository doctorSlotRepository;
	
	public DoctorSlotServiceImpl(DoctorSlotRepository doctorSlotRepository) {
		this.doctorSlotRepository=doctorSlotRepository;
	}

	@Override
	public Optional<DoctorSlots> getSlotById(long id) {
		if(!doctorSlotRepository.existsById(id)) {
			  throw new RuntimeException("DoctorSlot not found with id: " + id);
		}
		return doctorSlotRepository.findById(id);
	}

	@Override
	public void deleteSlot(long id) {
		if(!doctorSlotRepository.existsById(id)) {
			  throw new RuntimeException("Cannot Delete Because DoctorSlot not found with id: " + id);
		}
		
		doctorSlotRepository.deleteById(id);
		
	}

	@Override
	public List<DoctorSlots> getAllSlots() {
		
		return doctorSlotRepository.findAll();
	}

	@Override
	public DoctorSlots updateSlot(long id, DoctorSlots doctorSlot) {
		DoctorSlots existing =doctorSlotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("doctorSlot not found with id: " + id));
		existing.setStaffId(doctorSlot.getStaffId());
		existing.setDayOfWeek(doctorSlot.getDayOfWeek());
		existing.setSlotId(doctorSlot.getSlotId());
		existing.setIsActive(doctorSlot.getIsActive());
		doctorSlotRepository.save(existing);
		return existing;
	}

	@Override
	public DoctorSlots createSlot(DoctorSlots doctorSlot) {
		if(doctorSlot==null) {
			throw new RuntimeException("DoctorSlot is Null & No value present " );
		}
		return doctorSlotRepository.save(doctorSlot);
	}



}
