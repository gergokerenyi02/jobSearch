package com.example.jobsearch.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public ApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
