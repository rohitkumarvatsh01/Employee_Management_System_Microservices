package com.departmentservice.exception;

public class DepartmentNotFoundException extends RuntimeException{

    public DepartmentNotFoundException(String messgae){
        super(messgae);
    }
}
