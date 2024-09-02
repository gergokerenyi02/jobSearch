package com.example.jobsearch.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class ValidationException extends RuntimeException {

    private final HttpStatus status;

    public ValidationException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}