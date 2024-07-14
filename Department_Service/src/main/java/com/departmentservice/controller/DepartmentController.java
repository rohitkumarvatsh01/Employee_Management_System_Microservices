package com.departmentservice.controller;

import com.departmentservice.dto.DepartmentDTO;
import com.departmentservice.model.Department;
import com.departmentservice.service.DepartmentRestTemplateService;
import com.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentRestTemplateService departmentRestTemplateService;

    // Create a new record in the table.
    @PostMapping("/create")
    public ResponseEntity<Department> createNewDepartment(@RequestBody Department department) {
        Department result = departmentService.createNewDepartment(department);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // Retrieve a record from the database by ID.
    @GetMapping("/get/{dept_id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long dept_id) {
        Department result = departmentService.getDepartmentById(dept_id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Retrieve all records from the database.
    @GetMapping("/get")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> result = departmentService.getAllDepartments();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Update a record in the table by ID.
    @PutMapping("/update/{dept_id}")
    public ResponseEntity<Department> updateDepartmentById(@PathVariable Long dept_id, @RequestBody Department department) {
        Department result = departmentService.updateDepartmentById(dept_id, department);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Delete a record from the table by ID.
    @DeleteMapping("/delete/{dept_id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable Long dept_id) {
        String result = departmentService.deleteDepartmentById(dept_id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Delete all records from the table.
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAllDepartments() {
        String result = departmentService.deleteAllDepartments();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Get Employee details including Department by Department ID
    @GetMapping("/employee/get/{deptid}")
    public ResponseEntity<DepartmentDTO> getDepartmentEmployeeByDeptId(@PathVariable Long deptid) {
        DepartmentDTO dto = (DepartmentDTO) departmentRestTemplateService.getDepartmentEmployeeByDeptId(deptid);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get All Employee details including Department
    @GetMapping("/employee/get")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartmentEmployee() {
        List<DepartmentDTO> list = departmentRestTemplateService.getAllDepartmentEmployee();

        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }
}