package com.employeeservice.controller;

import com.employeeservice.dto.EmployeeDTO;
import com.employeeservice.model.Employee;
import com.employeeservice.service.EmployeeRestTemplateService;
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

    @Autowired
    private EmployeeRestTemplateService employeeRestTemplateService;

    // Create a new record in the table.
    @PostMapping("/create")
    public ResponseEntity<Employee> createNewRecord(@RequestBody Employee employee) {
        Employee result = employeeService.createNewRecord(employee);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // Retrieve all records from the database.
    @GetMapping("/get")
    public ResponseEntity<List<Employee>> getAllRecords() {
        List<Employee> result = employeeService.getAllRecords();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Retrieve a record from the database by ID.
    @GetMapping("/get/{empid}")
    public ResponseEntity<Employee> getRecordById(@PathVariable Long empid) {
        Employee result = employeeService.getRecordById(empid);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update a record in the table by ID.
    @PutMapping("/update/{empid}")
    public ResponseEntity<Employee> updateById(@PathVariable long empid, @RequestBody Employee employee) {
        Employee result = employeeService.updateById(empid, employee);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a record from the table by ID.
    @DeleteMapping("/delete/{empid}")
    public ResponseEntity<String> deleteById(@PathVariable long empid) {
        String result = employeeService.deleteById(empid);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete all records from the table.
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAllRecords() {
        String result = employeeService.deleteAllRecords();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Get Employee details including Department by Employee ID
    @GetMapping("/department/get/{empid}")
    public ResponseEntity<EmployeeDTO> getEmployeeDepartmentByEmpId(@PathVariable Long empid){
        EmployeeDTO dto = employeeRestTemplateService.getEmployeeDepartmentByEmpId(empid);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Get All Employee details including Department
    @GetMapping("/department/get")
    public ResponseEntity<List<EmployeeDTO>>getAllEmployeeDepartment(){
        List<EmployeeDTO> list=employeeRestTemplateService.getAllEmployeeDepartment();

        if(list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        }
    }
}
