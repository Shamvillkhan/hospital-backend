package com.hospital.management.service;

import com.hospital.management.entity.Testimonial;

import java.util.List;
import java.util.Optional;

public interface TestimonialService {

    Optional<List<Testimonial>> getList();
    Testimonial persist(Optional<Testimonial> testimonial);
    void deleteById(long id);
    Optional<Testimonial> findById(long id);

}
