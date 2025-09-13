package com.mybookingsservice.exceptions;

public class TokenPersistenceException extends RuntimeException {
    public TokenPersistenceException(String message) {
        super(message);
    }
}