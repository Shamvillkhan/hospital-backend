package com.hospital.management.controller;

import com.hospital.management.entity.Department;
import com.hospital.management.entity.Staff;
import com.hospital.management.entity.Staff.StaffRole;
import com.hospital.management.repository.DepartmentRepository;
import com.hospital.management.repository.StaffRepository;
import com.hospital.management.service.StaffService;

import jakarta.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;
    private StaffRepository staffRepository;
    private DepartmentRepository departmentRepository;

    public StaffController(StaffService staffService,StaffRepository staffRepository,DepartmentRepository departmentRepository) {
        this.staffService = staffService;
        this.staffRepository=staffRepository;
        this.departmentRepository=departmentRepository;
    }
    
    @GetMapping("/doctors/count")
    public long countDoctors() {
    	return staffRepository.countByRole(Staff.StaffRole.Doctor);
    }

    @GetMapping("/count")
    public long countStaffs() {
    	staffService.getAllDoctors();
        return staffRepository.count();
    }
    
    @GetMapping("/doctors")
    public List<Staff> getAllDoctors() {
    
        return staffService.getAllDoctors();
    }


   
    @PostMapping("/add")
    public ResponseEntity<Staff> createStaff(@Valid
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("role") StaffRole role,
            @RequestParam(value = "specialization", required = false) String specialization,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("hireDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hireDate,
            @RequestParam("departmentId") Long departmentId,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "workingDays", required = false) String workingDays,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) {
        Staff staff = new Staff();
        staff.setFirstName(firstName);
        staff.setLastName(lastName);
        staff.setRole(role);
        staff.setSpecialization(specialization);
        staff.setPhone(phone);
        staff.setEmail(email);
        staff.setHireDate(hireDate);
        
       
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + departmentId));
        staff.setDepartment(department);

        staff.setAddress(address);
        staff.setWorkingDays(workingDays);


        Staff saved = staffService.createStaff(image, staff);
        return ResponseEntity.ok(saved);
    }


     @GetMapping("/get/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable long id) {
        return ResponseEntity.ok(staffService.getStaffById(id));
    }
 
    @GetMapping("/getall")
    public ResponseEntity<List<Staff>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());
    }

 
    @PutMapping("/update/{id}")
    public ResponseEntity<Staff> updateStaff(@Valid @PathVariable long id, @RequestBody Staff staff) {
        return ResponseEntity.ok(staffService.updateStaff(id, staff));
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        staffRepository.deleteById(id);
        return ResponseEntity.ok("Deleted: " + id);
    }

}
