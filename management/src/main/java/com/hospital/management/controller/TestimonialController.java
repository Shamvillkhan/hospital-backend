package com.hospital.management.controller;

import com.hospital.management.entity.Testimonial;
import com.hospital.management.repository.TestimonialRepository;
import com.hospital.management.service.TestimonialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/testimonial")
@AllArgsConstructor
public class TestimonialController {
    private TestimonialService testimonialService;

    @GetMapping(path = "/get-all")
    public ResponseEntity<List<Testimonial>> getAll(){
     if (testimonialService.getList().isPresent()){

         return ResponseEntity.ok(testimonialService.getList().get());
     }
     else {

         throw new RuntimeException("data not found");
     }

    }
    @PostMapping(path = "/save")
    public ResponseEntity<Testimonial> save(@RequestBody Testimonial testimonial){



        return ResponseEntity.ok( testimonialService.persist(Optional.of(testimonial)));
    }
    @DeleteMapping(path = "/delete/{id}")
    public void deleteById(@PathVariable long id ){
        testimonialService.deleteById(id);

    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Testimonial> update(@PathVariable long id,@RequestBody Testimonial testimonial ){


        return  ResponseEntity.ok(testimonialService.update(id,Optional.of(testimonial)));
    }

}
