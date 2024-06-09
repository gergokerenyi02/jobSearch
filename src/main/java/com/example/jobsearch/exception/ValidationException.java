package com.example.jobsearch.exception;

// Kivétel osztály létrehozása a validációs hibákra

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}