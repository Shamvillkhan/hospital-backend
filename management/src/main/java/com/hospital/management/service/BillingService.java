package com.hospital.management.service;

import com.hospital.management.entity.Billing;

import java.util.List;
import java.util.Optional;

public interface BillingService {
    Optional<List<Billing>> getList();
    Optional<Billing>   findById(long id);
    void deleteById(long id);
    Billing persist(Optional<Billing> billing);

}
