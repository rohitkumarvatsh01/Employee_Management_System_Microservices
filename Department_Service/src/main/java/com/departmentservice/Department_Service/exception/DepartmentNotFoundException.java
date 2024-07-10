package com.departmentservice.Department_Service.exception;

public class DepartmentNotFoundException extends RuntimeException{

    public DepartmentNotFoundException(String messgae){
        super(messgae);
    }
}
