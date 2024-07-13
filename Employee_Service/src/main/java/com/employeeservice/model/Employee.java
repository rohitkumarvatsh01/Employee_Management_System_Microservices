package com.employeeservice.model;

import com.departmentservice.model.Department;
import jakarta.persistence.*;

@Entity
@Table(name="Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long empid;

    @Column(name="emp_name")
    private String emp_name;

    @Column(name="emp_age")
    private Integer emp_age;

    @Column(name="emp_salary")
    private float emp_salary;

    @Column(name="dept_id")
    private Long dept_id;

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

    public Long getDept_id() {
        return dept_id;
    }

    public void setDept_id(Long dept_id) {
        this.dept_id = dept_id;
    }

    public Employee(){

    }

    public Employee(Long empid, String emp_name, Integer emp_age, float emp_salary, Long dept_id) {
        this.empid = empid;
        this.emp_name = emp_name;
        this.emp_age = emp_age;
        this.emp_salary = emp_salary;
        this.dept_id = dept_id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empid=" + empid +
                ", emp_name='" + emp_name + '\'' +
                ", emp_age=" + emp_age +
                ", emp_salary=" + emp_salary +
                ", dept_id=" + dept_id +
                '}';
    }
}
