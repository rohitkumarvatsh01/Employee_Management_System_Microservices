package com.departmentservice.model;

import jakarta.persistence.*;


@Entity
@Table(name="Department")
public class Department {

    @Id
    @Column(name = "deptid")
    private Long deptid;

    @Column(name = "dept_name")
    private String dept_name;

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

    public Department(){

    }

    public Department(Long deptid, String dept_name) {
        this.deptid = deptid;
        this.dept_name = dept_name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptid=" + deptid +
                ", dept_name='" + dept_name + '\'' +
                '}';
    }
}
