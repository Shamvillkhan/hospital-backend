package com.hospital.management.service;

import com.hospital.management.entity.Billing;
import com.hospital.management.repository.BillingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BillingServiceImpl implements BillingService {

        private BillingRepository billingRepository;

    @Override
    public Optional<List<Billing>> getList() {
        return Optional.of(billingRepository.findAll());
    }

    @Override
    public Optional<Billing> findById(long id) {
        if (id!=0){
           return billingRepository.findById(id);

        }
        else {

         throw new RuntimeException("user id is not valid");
        }

    }

    @Override
    public void deleteById(long id) {

        if (id!=0){
            billingRepository.deleteById(id);
        }
        else {

            throw new RuntimeException("deleting user not found by this id");
        }
    }

    @Override
    public Billing persist(Optional<Billing> billing) {
       if (billing.isPresent()){

          return billingRepository.save(billing.get());
       }
       else {
           throw new RuntimeException("user object is null");
       }
    }
}
