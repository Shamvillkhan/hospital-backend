package com.hospital.management.controller;

import com.hospital.management.entity.Billitems;
import com.hospital.management.service.BillitemsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/billitems")
public class BillitemsController {

    private BillitemsService billitemsService;

    public BillitemsController(BillitemsService billitemsService) {
        this.billitemsService = billitemsService;
    }


    @GetMapping(path = "/get-all")
    public ResponseEntity<List<Billitems>> getAll(){
        if (billitemsService.getList().isPresent()){
            return ResponseEntity.ok(billitemsService.getList().get());
        }
        else {
            throw new RuntimeException("billitems is null in controller");
        }

    }
    @PostMapping(path = "/save")
    public ResponseEntity<Billitems> save(@RequestBody Billitems billitems) {

        Optional<Billitems> billitems1 = billitemsService.save(Optional.of(billitems));
        if (billitems1.isPresent()) {

            return ResponseEntity.ok(billitems1.get());
        } else {
            throw new RuntimeException("saved entity is null");
        }
    }
        @DeleteMapping(path = "/delete/{id}")
        public String deleteById(@PathVariable long id ){
              billitemsService.deleteById(id);
        return "user deleted by this id:-"+id;
        }

        @PutMapping(path = "/update/{id}")
        public ResponseEntity<Billitems> update(@PathVariable long id,@RequestBody Billitems billitems){

            return ResponseEntity.ok(billitemsService.update(id,Optional.of(billitems)).get());

        }

}
