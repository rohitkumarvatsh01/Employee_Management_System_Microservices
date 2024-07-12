package com.employeeservice.dto;

public class DepartmentDTO {

    private Long emp_id;
    private String emp_name;

    public Long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Long emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public DepartmentDTO(Long emp_id, String emp_name) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
    }

    public DepartmentDTO() {
        super();
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "emp_id=" + emp_id +
                ", emp_name='" + emp_name + '\'' +
                '}';
    }
}
