package com.hospital.management.service;

import com.hospital.management.entity.Rooms;

import java.util.List;
import java.util.Optional;

public interface RoomsService {
    Optional<List<Rooms>> getList();
    void deleteById(long id);
    Optional<Rooms>findById(long id);
    Rooms persist(Optional<Rooms> rooms);


}
