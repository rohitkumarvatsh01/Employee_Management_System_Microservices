package com.employeeservice.service;

import com.departmentservice.model.Department;
import com.employeeservice.dto.EmployeeDTO;
import com.employeeservice.exception.EmployeeNotFoundException;
import com.employeeservice.model.Employee;
import com.employeeservice.repository.EmployeeRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeRestTemplate {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String departmentServiceURL = "http://localhost:8082/department/";

    //
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

            Department department = restTemplate.getForObject(departmentServiceURL + "get/" + employee.getDept_id(), Department.class);

            if (department != null) {
                employeeDTO.setDept_name(department.getDept_name());
            }

            logger.info("Retrieved employee details with department: {}", employeeDTO);
            return employeeDTO;
        } else {
            logger.warn("Employee not found with ID: {}", empId);
            throw new EmployeeNotFoundException("Employee not found with ID: " + empId);
        }
    }

    public List<EmployeeDTO> getAllEmployeeDepartment() {

        List<Employee> list=employeeRepository.findAll();

    }
}
