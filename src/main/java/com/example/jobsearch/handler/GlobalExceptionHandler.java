package com.example.jobsearch.handler;

import com.example.jobsearch.exception.ApiException;
import com.example.jobsearch.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Kivétel kezelő osztály

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder errorMessage = new StringBuilder();

        for (FieldError fieldError : fieldErrors) {
            errorMessage.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
    }


    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, String>> handleApiException(ApiException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Api Error");
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }
}