package com.departmentservice.Department_Service.exception;

public class InvalidDepartmentDataException extends RuntimeException{
    public InvalidDepartmentDataException(String message){
        super(message);
    }
}
