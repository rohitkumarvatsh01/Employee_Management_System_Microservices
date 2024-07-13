package com.departmentservice.service;

import com.departmentservice.dto.DepartmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class DepartmentRestTemplateService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String employeeServiceURL = "http://localhost:8081/employee/";

    // Get Employee details including Department by Employee ID
    public DepartmentDTO getDepartmentEmployeeByEmpId(Long deptid) {
        String employeeUrl = employeeServiceURL + "get/" + deptid;
        ResponseEntity<DepartmentDTO> response = restTemplate.getForEntity(employeeUrl, DepartmentDTO.class);
        if (response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }

    // Get All Employee details including Department
    public List<DepartmentDTO> getAllDepartmentEmployee() {
        ResponseEntity<DepartmentDTO[]> response = restTemplate.getForEntity(employeeServiceURL + "get", DepartmentDTO[].class);
        DepartmentDTO[] departmentDTOs = response.getBody();
        if (departmentDTOs != null && departmentDTOs.length > 0) {
            return Arrays.asList(departmentDTOs);
        } else {
            return null;
        }
    }
}
