package com.example.jobsearch.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class RegistrationException extends RuntimeException {

    private final HttpStatus status;

    public RegistrationException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}