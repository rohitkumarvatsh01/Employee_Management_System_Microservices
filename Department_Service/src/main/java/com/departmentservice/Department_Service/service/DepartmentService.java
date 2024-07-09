package com.departmentservice.Department_Service.service;

import com.departmentservice.Department_Service.model.Department;
import com.departmentservice.Department_Service.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department createNewDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Optional<Department> getDeparmentById(Long deptId) {
        Optional<Department>optional=departmentRepository.findById(deptId);
        return optional;
    }
}
