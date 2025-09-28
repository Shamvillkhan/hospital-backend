package com.hospital.management.service;

import com.hospital.management.entity.Billitems;


import java.util.List;
import java.util.Optional;

public interface BillitemsService {
    Optional<List<Billitems>>  getList();
    void deleteById(long id);
    Optional<Billitems> findById(long id);
    Optional<Billitems> save(Optional<Billitems> billitems);
    Optional<Billitems> update(long id,Optional<Billitems> billitems);
}
