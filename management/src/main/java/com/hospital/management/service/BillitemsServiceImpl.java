package com.hospital.management.service;

import com.hospital.management.entity.Billitems;
import com.hospital.management.repository.BillitemsRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillitemsServiceImpl implements BillitemsService{

    private BillitemsRepository billitemsRepository;

    public BillitemsServiceImpl(BillitemsRepository billitemsRepository) {
        this.billitemsRepository = billitemsRepository;
    }

    @Override
    public Optional<List<Billitems>> getList() {
        return Optional.of(billitemsRepository.findAll());
    }

    @Override
    public void deleteById(long id) {
    if (id!=0){

        billitemsRepository.deleteById(id);
    }
    else {
        throw new RuntimeException("invalid id for delete user ");
    }

    }

    @Override
    public Optional<Billitems> findById(long id) {

        if (id!=0){
         return   billitemsRepository.findById(id);
        }
        else {
            throw new RuntimeException("user not fount by this id:-"+id);
        }

    }

    @Override
    public Optional<Billitems> save(Optional<Billitems> billitems) {

         if (billitems.isPresent()){

            return Optional.of( billitemsRepository.save(billitems.get()));
         }
         else {

             throw new RuntimeException("object that you want go save is null");
         }


    }

    @Override
    public Optional<Billitems> update(long id,Optional<Billitems> billitems) {

        if (id!=0 && billitems.isPresent()) {

            Optional<Billitems> billitems1 = billitemsRepository.findById(id);

            if (billitems1.isPresent()) {
                billitems1.get().setBilling(billitems.get().getBilling());
                billitems1.get().setUnitPrice(billitems.get().getUnitPrice());
                billitems1.get().setQuantity(billitems.get().getQuantity());
                billitems1.get().setDescription(billitems.get().getDescription());
                return Optional.of(billitemsRepository.save(billitems1.get()));
            } else {

                throw new RuntimeException("billitems is null which we get in the database");
            }
        }
        else {
            throw new RuntimeException("invalid user id:-"+id+"  or object which you give to update is null");
        }

    }
}
