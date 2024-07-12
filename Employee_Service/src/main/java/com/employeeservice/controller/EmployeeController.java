package com.employeeservice.controller;

import com.departmentservice.model.Department;
import com.employeeservice.dto.DepartmentDTO;
import com.employeeservice.model.Employee;
import com.employeeservice.repository.EmployeeRepository;
import com.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestTemplate restTemplate;

    /*@GetMapping("/rest/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            // Assuming department ID is same as employee ID for simplicity
            DepartmentDTO department = restTemplate.getForObject("http://localhost:8082/department/get/" + id, DepartmentDTO.class);
            // You can add department details to employee if needed
            // For example: employee.setDepartment(department);
        }
        return employee;
    }*/

    @GetMapping("/rest/{empid}")
    public ResponseEntity<Department> getDepartmentForEmployee(@PathVariable Long empid) {
        Department department = employeeService.getDepartmentForEmployee(empid);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }


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
