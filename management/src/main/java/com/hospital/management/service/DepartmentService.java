package com.hospital.management.service;



import java.util.List;

import com.hospital.management.entity.Department;

public interface DepartmentService {

    Department createDepartment(Department department);

    Department updateDepartment(long id, Department department);

    void deleteDepartment(long id);

    Department getDepartmentById(long id);

    List<Department> getAllDepartments();
}
