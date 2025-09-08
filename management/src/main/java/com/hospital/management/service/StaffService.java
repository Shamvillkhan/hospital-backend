package com.hospital.management.service;

import com.hospital.management.entity.Staff;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface StaffService {
    Staff createStaff(MultipartFile  image,Staff staff);
    Staff getStaffById(long staffId);
    List<Staff> getAllStaff();
    Staff updateStaff(long staffId, Staff staffDetails);
    void deleteStaff(long staffId);
    List<Staff> getAllDoctors();
}
