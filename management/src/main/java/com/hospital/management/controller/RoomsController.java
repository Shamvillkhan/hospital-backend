package com.hospital.management.controller;

import com.hospital.management.entity.Rooms;
import com.hospital.management.service.RoomsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/rooms")
@AllArgsConstructor
public class RoomsController {

    private RoomsService roomsService;

    @GetMapping(path = "/get-all")
    public ResponseEntity<List<Rooms>> getRooms(){

        if (roomsService.getList().isPresent()){
             return ResponseEntity.ok(roomsService.getList().get());
        }
        else {

            throw new RuntimeException("list not found");
        }

    }

    @PostMapping(path = "save")
    public ResponseEntity<Rooms> save(@RequestBody Rooms rooms){

           Rooms rooms1= roomsService.persist(Optional.of(rooms));


        return ResponseEntity.ok(rooms1);
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteById(@PathVariable long id){

        roomsService.deleteById(id);

    }

    @PutMapping(path = "update")
    public ResponseEntity<Rooms> update( @RequestBody Rooms rooms){

       Rooms rooms1= roomsService.persist(Optional.of(rooms));

        return ResponseEntity.ok(rooms1);
    }

}
