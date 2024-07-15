package com.employeeservice.dto;

import jakarta.persistence.Column;

public class EmployeeDTO {

    private Long empid;
    private String emp_name;
    private Integer emp_age;
    private float emp_salary;
    private Long deptid;
    private String dept_name;

    public Long getEmpid() {
        return empid;
    }

    public void setEmpid(Long empid) {
        this.empid = empid;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public Integer getEmp_age() {
        return emp_age;
    }

    public void setEmp_age(Integer emp_age) {
        this.emp_age = emp_age;
    }

    public float getEmp_salary() {
        return emp_salary;
    }

    public void setEmp_salary(float emp_salary) {
        this.emp_salary = emp_salary;
    }

    public Long getDeptid() {
        return deptid;
    }

    public void setDeptid(Long dept_id) {
        this.deptid = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }
}
