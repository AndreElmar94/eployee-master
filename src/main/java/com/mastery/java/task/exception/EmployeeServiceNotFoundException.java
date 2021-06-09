package com.mastery.java.task.exception;

public class EmployeeServiceNotFoundException extends RuntimeException {

    public EmployeeServiceNotFoundException(String message) {
        super(message);
    }
}