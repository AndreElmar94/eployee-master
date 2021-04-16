package com.mastery.java.task.exception;

public class EmployeeNotFoundException extends EntityIsNotFoundException {

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}