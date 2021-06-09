package com.mastery.java.task.exception;

public class EmployeeIsNotFoundException extends RuntimeException {

    public EmployeeIsNotFoundException(String message) {
        super(message);
    }
}