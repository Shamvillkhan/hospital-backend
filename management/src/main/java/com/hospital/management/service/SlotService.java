package com.hospital.management.service;

import java.util.List;

import com.hospital.management.entity.Slots;



public interface SlotService {
    Slots createSlot(Slots slot);
    Slots getSlotById(long id);
    List<Slots> getAllSlots();
    Slots updateSlot(long id, Slots slot);
    void deleteSlot(long id);
}
