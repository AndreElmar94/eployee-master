package com.mastery.java.task.exception;

public class EntityIsNotFoundException extends RuntimeException {

    public EntityIsNotFoundException(String message) {
        super(message);
    }
}