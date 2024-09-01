package com.example.jobsearch.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class JobNotFoundException extends RuntimeException {

    private final HttpStatus status;

    public JobNotFoundException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
