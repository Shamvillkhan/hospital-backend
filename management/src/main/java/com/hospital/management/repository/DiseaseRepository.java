package com.hospital.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.management.entity.Disease;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

}
