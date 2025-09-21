package com.hospital.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hospital.management.entity.Slots;
import com.hospital.management.repository.SlotRepository;

@Service
public class SlotServiceImpl implements SlotService {

    private final SlotRepository slotRepository;
    
    public SlotServiceImpl(SlotRepository slotRepository) {
    	this.slotRepository=slotRepository;
    }

    @Override
    public Slots createSlot(Slots slot) {
        return slotRepository.save(slot);
    }

    @Override
    public List<Slots> getAllSlots() {
        return slotRepository.findAll();
    }

    @Override
    public Slots getSlotById(long id) {
        return slotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Slot not found with id " + id));
    }

    @Override
    public Slots updateSlot(long id, Slots slot) {
    	Slots existing = getSlotById(id);
        existing.setSlotName(slot.getSlotName());
        existing.setStartTime(slot.getStartTime());
        existing.setEndTime(slot.getEndTime());
        return slotRepository.save(existing);
    }

    @Override
    public void deleteSlot(long id) {
    	Slots slot = getSlotById(id);
        slotRepository.delete(slot);
    }
}
