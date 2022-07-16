package com.healthmanagement.labsapi.exception;

import com.healthmanagement.labsapi.exception.customs.LabNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisorExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LabNotFoundException.class)
    public ResponseEntity<Object> handleLabNotFoundException(LabNotFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Lab not found");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


}
