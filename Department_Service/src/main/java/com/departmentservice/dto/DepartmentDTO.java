package com.departmentservice.dto;

public class DepartmentDTO {

    private Long deptid;
    private String dept_name;
    private Long empid;
    private String emp_name;
    private Integer emp_age;
    private Float emp_salary;

    public Long getDeptid() {
        return deptid;
    }

    public void setDeptid(Long deptid) {
        this.deptid = deptid;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

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

    public Float getEmp_salary() {
        return emp_salary;
    }

    public void setEmp_salary(Float emp_salary) {
        this.emp_salary = emp_salary;
    }
}
