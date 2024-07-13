package com.employeeservice.service;

import com.departmentservice.model.Department;
import com.employeeservice.dto.EmployeeDTO;
import com.employeeservice.exception.EmployeeNotFoundException;
import com.employeeservice.model.Employee;
import com.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeRestTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeRestTemplateService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String departmentServiceURL = "http://localhost:8082/department/";

    // Get Employee with department details by ID
    public EmployeeDTO getEmployeeDepartmentByEmpId(Long empId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            EmployeeDTO employeeDTO = new EmployeeDTO();

            employeeDTO.setEmpid(employee.getEmpid());
            employeeDTO.setEmp_name(employee.getEmp_name());
            employeeDTO.setEmp_age(employee.getEmp_age());
            employeeDTO.setEmp_salary(employee.getEmp_salary());
            employeeDTO.setDept_id(employee.getDept_id());

            try {
                Department department = restTemplate.getForObject(departmentServiceURL + "get/" + employee.getDept_id(), Department.class);
                if (department != null) {
                    employeeDTO.setDept_name(department.getDept_name());
                } else {
                    logger.warn("Department not found for Department ID: {}", employee.getDept_id());
                }
            } catch (Exception e) {
                logger.error("Error retrieving department details for Employee ID: {}", empId, e);
                throw new EmployeeNotFoundException("Error retrieving department details for Employee ID: " + e);
            }

            logger.info("Retrieved employee details with department: {}", employeeDTO);
            return employeeDTO;
        } else {
            logger.warn("Employee not found with ID: {}", empId);
            throw new EmployeeNotFoundException("Employee not found with ID: " + empId);
        }
    }

    // Get All Employees with department details
    public List<EmployeeDTO> getAllEmployeeDepartment() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();

        if (employees.isEmpty()) {
            logger.warn("No employees found in the database");
            throw new EmployeeNotFoundException("No employees found in the database");
        }

        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmpid(employee.getEmpid());
            employeeDTO.setEmp_name(employee.getEmp_name());
            employeeDTO.setEmp_age(employee.getEmp_age());
            employeeDTO.setEmp_salary(employee.getEmp_salary());
            employeeDTO.setDept_id(employee.getDept_id());

            try {
                Department department = restTemplate.getForObject(departmentServiceURL + "get/" + employee.getDept_id(), Department.class);
                if (department != null) {
                    employeeDTO.setDept_name(department.getDept_name());
                } else {
                    logger.warn("Department not found for Department ID: {}", employee.getDept_id());
                }
            } catch (Exception e) {
                logger.error("Error retrieving department details for Employee ID: {}", employee.getEmpid(), e);
                // Log the error and continue with other employees
            }

            employeeDTOs.add(employeeDTO);
        }

        logger.info("Retrieved {} employee records with department details", employeeDTOs.size());
        return employeeDTOs;
    }
}
