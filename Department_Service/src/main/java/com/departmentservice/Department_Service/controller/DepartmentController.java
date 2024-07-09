package com.departmentservice.Department_Service.controller;

import com.departmentservice.Department_Service.model.Department;
import com.departmentservice.Department_Service.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<Department>createNewDepartment(@RequestBody Department department){
        Department result=departmentService.createNewDepartment(department);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/get/{dept_id}")
    public ResponseEntity<Department>getDepartmentById(@PathVariable Long dept_id){
        Optional<Department> result=departmentService.getDeparmentById(dept_id);
        return new ResponseEntity<>(result, HttpStatus.FOUND);
    }
}
