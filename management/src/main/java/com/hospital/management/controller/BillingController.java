package com.hospital.management.controller;

import com.hospital.management.entity.Billing;
import com.hospital.management.service.BillingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@RestController
@RequestMapping(path = "/billing")
@AllArgsConstructor
public class BillingController {
    private BillingService billingService;

    @GetMapping(path = "/get-all")
    public ResponseEntity<List<Billing>> getAll(){
        if (billingService.getList().isPresent())
        {
            return  ResponseEntity.ok(billingService.getList().get());
        }
        else {

            throw new RuntimeException("billing list is null");
        }

    }
    @PostMapping(path = "/save")
    public ResponseEntity<Billing>  save(@RequestBody Billing billing){


        return ResponseEntity.ok(billingService.persist(Optional.of(billing)));
    }


 }
