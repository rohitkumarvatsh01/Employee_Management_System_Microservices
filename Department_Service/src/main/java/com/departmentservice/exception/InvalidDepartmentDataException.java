package com.departmentservice.exception;

public class InvalidDepartmentDataException extends RuntimeException{
    public InvalidDepartmentDataException(String message){
        super(message);
    }
}
