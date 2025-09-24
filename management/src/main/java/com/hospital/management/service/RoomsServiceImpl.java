package com.hospital.management.service;

import com.hospital.management.entity.Rooms;
import com.hospital.management.repository.RoomsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class RoomsServiceImpl implements RoomsService{

    private RoomsRepository roomsRepository;


    @Override
    public Optional<List<Rooms>> getList()throws RuntimeException {


        return  Optional.of(roomsRepository.findAll());
    }

    @Override
    public void deleteById(long id)throws RuntimeException {
        if(id!=0){
            roomsRepository.deleteById(id);
        }

        else {

           throw  new RuntimeException("delet id is wrong ");
        }
    }

    @Override
    public Optional<Rooms> findById(long id) {

        if(id!=0){

          return   roomsRepository.findById(id);

        }
        else {

            throw new RuntimeException("id is invalid");
        }


    }

    @Override
    public Rooms persist(Optional<Rooms> rooms){
        if(rooms.isEmpty()){

            throw new RuntimeException("entity not found");
        }
        return roomsRepository.save(rooms.get());
    }
}
