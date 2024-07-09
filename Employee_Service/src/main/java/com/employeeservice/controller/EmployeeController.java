package com.employeeservice.controller;

import com.employeeservice.model.Employee;
import com.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Create a new record in the table.
    @PostMapping("/create")
    public ResponseEntity<Employee> createNewRecord(@RequestBody Employee employee) {
        Employee result = employeeService.createNewRecord(employee);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // Retrieve all records from the database.
    @GetMapping("/get")
    public ResponseEntity<List<Employee>> getAllRecords() {
        return new ResponseEntity<>(employeeService.getAllRecords(), HttpStatus.OK);
    }

    // Retrieve a record from the database by ID.
    @GetMapping("/get/{empid}")
    public ResponseEntity<Employee>getRecordById(@PathVariable Long empid){
        Employee result = employeeService.getRecordById(empid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Update a record in the table by ID.
    @PutMapping("/update/{empid}")
    public ResponseEntity<Employee> updateById(@PathVariable long empid, @RequestBody Employee employee) {
        Employee result = employeeService.updateById(empid, employee);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Delete a record from the table by ID.
    @DeleteMapping("/delete/{empid}")
    public ResponseEntity<String> deleteById(@PathVariable long empid) {
        String result = employeeService.deleteById(empid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Delete all records from the table.
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAllRecords() {
        String result = employeeService.deleteAllRecords();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
