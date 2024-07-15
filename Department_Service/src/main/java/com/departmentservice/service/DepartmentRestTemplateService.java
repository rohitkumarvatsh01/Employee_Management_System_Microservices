package com.departmentservice.service;

import com.departmentservice.dto.DepartmentDTO;
import com.departmentservice.dto.EmployeeDTO;
import com.departmentservice.model.Department;
import com.departmentservice.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DepartmentRestTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentRestTemplateService.class);

    private static final String employeeServiceURL = "http://localhost:8081/employee/";

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RestTemplate restTemplate;

    public DepartmentDTO getDepartmentEmployeeByDeptId(Long deptid) {
        Department department = departmentRepository.findById(deptid).orElse(null);

        if (department != null) {
            EmployeeDTO employee = restTemplate.getForObject(employeeServiceURL + "getByDeptId/" + deptid, EmployeeDTO.class);

            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setDeptid(department.getDeptid());
            departmentDTO.setDept_name(department.getDept_name());
            departmentDTO.setEmployee(employee);

            return departmentDTO;
        } else {
            logger.warn("Department not found with ID: {}", deptid);
            return null;
        }
    }

    // Get Employee details including Department by Department ID
/*public DepartmentDTO getDepartmentEmployeeByDeptId(Long deptid) {

    Optional<Department> optionalDepartment = departmentRepository.findById(deptid);

    if (optionalDepartment.isPresent()) {
        Department department = optionalDepartment.get();

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDeptid(department.getDeptid());
        departmentDTO.setDept_name(department.getDept_name());

        try {
            Employee employee = restTemplate.getForObject(employeeServiceURL + "get/" + department.getDeptid(), Employee.class);
            if (employee != null) {
                departmentDTO.setEmpid(employee.getEmpid());
                departmentDTO.setEmp_name(employee.getEmp_name());
                departmentDTO.setEmp_age(employee.getEmp_age());
                departmentDTO.setEmp_salary(employee.getEmp_salary());
            } else {
                logger.warn("Employee not found for Department ID: {}", department.getDeptid());
            }
        } catch (Exception e) {
            logger.error("Error retrieving employee details for Department ID: {}", deptid, e);
            throw new DepartmentNotFoundException("Error retrieving employee details for Department ID: " + deptid);
        }

        logger.info("Retrieved department details with employee: {}", departmentDTO);
        return departmentDTO;
    } else {
        logger.warn("Department not found with ID: {}", deptid);
        throw new DepartmentNotFoundException("Department not found with ID: " + deptid);
    }
}

// Get All Employee details including Department
public List<DepartmentDTO> getAllDepartmentEmployee() {
    List<Department> departments = departmentRepository.findAll();
    List<DepartmentDTO> departmentDTOs = new ArrayList<>();

    if (departments.isEmpty()) {
        logger.warn("No departments found in the database");
        throw new DepartmentNotFoundException("No departments found in the database");
    }

    for (Department department : departments) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDeptid(department.getDeptid());
        departmentDTO.setDept_name(department.getDept_name());

        try {
            Employee employee = restTemplate.getForObject(employeeServiceURL + "get/" + department.getDeptid(), Employee.class);
            if (employee != null) {
                departmentDTO.setEmpid(employee.getEmpid());
                departmentDTO.setEmp_name(employee.getEmp_name());
                departmentDTO.setEmp_age(employee.getEmp_age());
                departmentDTO.setEmp_salary(employee.getEmp_salary());
            } else {
                logger.warn("Employee not found for Department ID: {}", department.getDeptid());
            }
        } catch (Exception e) {
            logger.error("Error retrieving employee details for Department ID: {}", department.getDeptid(), e);
            // Log the error and continue with other departments
        }

        departmentDTOs.add(departmentDTO);
    }

    logger.info("Retrieved {} department records with employee details", departmentDTOs.size());
    return departmentDTOs;
}*/
}
