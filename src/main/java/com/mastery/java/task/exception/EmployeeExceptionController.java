package com.mastery.java.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeExceptionController {

    @ExceptionHandler(value = EmployeeServiceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exception(EmployeeServiceNotFoundException exception) {
                return exception.getMessage();
    }
}
