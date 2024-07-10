package com.departmentservice.service;

import com.departmentservice.exception.DepartmentNotFoundException;
import com.departmentservice.exception.InvalidDepartmentDataException;
import com.departmentservice.model.Department;
import com.departmentservice.repository.DepartmentRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    // Create a new department record.
    public Department createNewDepartment(Department department) {
        validateDepartmentData(department);
        logger.info("Adding new department: {}", department);
        return departmentRepository.save(department);
    }

    // Validate department data
    private void validateDepartmentData(Department department) {
        if (department.getDept_name() == null || department.getDept_name().isEmpty()) {
            logger.warn("Invalid department name: {}", department.getDept_name());
            throw new InvalidDepartmentDataException("Department name is invalid");
        }
    }

    // Retrieve a department record by ID.
    public Department getDepartmentById(Long deptId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(deptId);

        if (optionalDepartment.isPresent()) {
            logger.info("Department found with ID: {}", deptId);
            return optionalDepartment.get();
        } else {
            logger.warn("Department not found with ID: {}", deptId);
            throw new DepartmentNotFoundException("Department not found with ID " + deptId);
        }
    }

    // Retrieve all department records.
    public List<Department> getAllDepartments() {
        List<Department> departmentList = departmentRepository.findAll();
        if (departmentList.isEmpty()) {
            logger.info("No department records found");
            throw new DepartmentNotFoundException("No department records found");
        } else {
            logger.info("Retrieved all department records");
            return departmentList;
        }
    }

    // Update a department record by ID.
    public Department updateDepartmentById(Long deptId, Department department) {
        Optional<Department> optionalDepartment = departmentRepository.findById(deptId);

        if (optionalDepartment.isPresent()) {
            Department existingDepartment = optionalDepartment.get();
            existingDepartment.setDept_name(department.getDept_name());
            validateDepartmentData(existingDepartment);
            logger.info("Updated department with ID: {}", deptId);
            return departmentRepository.save(existingDepartment);
        } else {
            logger.warn("Department not found with ID: {}", deptId);
            throw new DepartmentNotFoundException("Department not found with ID " + deptId);
        }
    }

    // Delete a department record by ID.
    public String deleteDepartmentById(Long deptId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(deptId);

        if (optionalDepartment.isPresent()) {
            departmentRepository.deleteById(deptId);
            logger.info("Deleted department with ID: {}", deptId);
            return "Department with ID " + deptId + " is deleted";
        } else {
            logger.warn("Department not found with ID: {}", deptId);
            throw new DepartmentNotFoundException("Department not found with ID " + deptId);
        }
    }

    // Delete all department records.
    public String deleteAllDepartments() {
        List<Department> departments = departmentRepository.findAll();

        if (departments.isEmpty()) {
            logger.info("No department records found to delete");
            throw new DepartmentNotFoundException("No department records found to delete");
        } else {
            departmentRepository.deleteAll();
            logger.info("Deleted all department records");
            return "All department records are deleted";
        }
    }
}