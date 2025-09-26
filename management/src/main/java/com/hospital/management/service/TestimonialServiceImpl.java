package com.hospital.management.service;

import com.hospital.management.entity.Testimonial;
import com.hospital.management.repository.TestimonialRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TestimonialServiceImpl implements TestimonialService{

    private TestimonialRepository testimonialRepository;

    @Override
    public Optional<List<Testimonial>> getList() {
        return Optional.of(testimonialRepository.findAll());
    }

    @Override
    public Testimonial persist(Optional<Testimonial> testimonial) {
      if (testimonial.isPresent()){
        return  testimonialRepository.save(testimonial.get());
      }
    else {
        throw new RuntimeException("user data error");
      }

    }

    @Override
    public void deleteById(long id) {

        if(id!=0){
            testimonialRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("delete user not found");
        }
    }

    @Override
    public Optional<Testimonial> findById(long id) {
       if(id!=0){


          return testimonialRepository.findById(id);
       }
       else{
           throw new RuntimeException("user not found by this id");
       }

    }

    @Override
    public Testimonial update(long id, Optional<Testimonial> testimonial) {
        if (id!=0){
         Testimonial testimonial1=   this.findById(id).get();

          Testimonial testimonial2=  testimonial.get();

         testimonial1.setName(testimonial2.getName());
          testimonial1.setEmail(testimonial2.getEmail());
          testimonial1.setMessage(testimonial2.getMessage());
          testimonial1.setRating(testimonial2.getRating());

        return   testimonialRepository.save(testimonial1);

        }
        else {

            throw new RuntimeException("id not found");
        }


    }
}
