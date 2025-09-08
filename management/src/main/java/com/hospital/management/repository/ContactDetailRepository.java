package com.hospital.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.management.entity.ContactDetail;

@Repository
public interface ContactDetailRepository extends JpaRepository<ContactDetail, Long> {
	
	List<ContactDetail> findAllByActiveTrue();

}
