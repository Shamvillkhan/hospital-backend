package com.hospital.management.service;

import org.springframework.stereotype.Service;

import com.hospital.management.entity.Department;
import com.hospital.management.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

   
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(long id, Department department) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        existing.setName(department.getName());
        existing.setDescription(department.getDescription());
        existing.setHeadOfDepartment(department.getHeadOfDepartment());
        existing.setContactNumber(department.getContactNumber());
        existing.setLocation(department.getLocation());

        return departmentRepository.save(existing);
    }

    @Override
    public void deleteDepartment(long id) {
        if (!departmentRepository.existsById(id)) {
            throw new RuntimeException("Department not found with id: " + id);
        }
        departmentRepository.deleteById(id);
    }

    @Override
    public Department getDepartmentById(long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}
