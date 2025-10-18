package com.hospital.management.repository;

import com.hospital.management.entity.Staff;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    // ✅ Example: find by email
    boolean existsByEmail(String email);

    // ✅ Example: find staff by role
    List<Staff> findByRole(Staff.StaffRole role);


    long countByRole(Staff.StaffRole role); // ✅ returns long

}
