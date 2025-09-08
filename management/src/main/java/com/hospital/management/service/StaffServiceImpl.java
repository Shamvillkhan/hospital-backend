package com.hospital.management.service;

import com.hospital.management.entity.Staff;
import com.hospital.management.fileutility.FileStorageService;
import com.hospital.management.repository.StaffRepository;
import com.hospital.management.service.StaffService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public Staff createStaff(MultipartFile  image,Staff staff) {
    	  if (image != null && !image.isEmpty()) {
	            String fileName = FileStorageService.storeFile(image);
	            staff.setImage(fileName); 
	            staff.setImagePath(FileStorageService.getUploadDir()+"/"+fileName);
	        }
        return staffRepository.save(staff);
    }

    @Override
    public Staff getStaffById(long staffId) {
        return staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff updateStaff(long staffId, Staff staffDetails) {
        Staff existingStaff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));

        existingStaff.setFirstName(staffDetails.getFirstName());
        existingStaff.setLastName(staffDetails.getLastName());
        existingStaff.setRole(staffDetails.getRole());
        existingStaff.setSpecialization(staffDetails.getSpecialization());
        existingStaff.setPhone(staffDetails.getPhone());
        existingStaff.setEmail(staffDetails.getEmail());
        existingStaff.setHireDate(staffDetails.getHireDate());
        existingStaff.setDepartment(staffDetails.getDepartment());

        return staffRepository.save(existingStaff);
    }
    	
 
    public List<Staff> getAllDoctors() {
        return staffRepository.findByRole(Staff.StaffRole.Doctor);
    }


    @Override

    public void deleteStaff(long id) {
        if (!staffRepository.existsById(id)) {
            throw new RuntimeException("Staff not found with id: " + id);
        }
        staffRepository.deleteById(id);
    }


}
