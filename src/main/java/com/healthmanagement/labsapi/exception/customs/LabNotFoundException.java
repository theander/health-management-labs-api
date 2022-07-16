package com.healthmanagement.labsapi.exception.customs;

public class LabNotFoundException extends RuntimeException{
    public LabNotFoundException(String message) {
        super(message);
    }
}
