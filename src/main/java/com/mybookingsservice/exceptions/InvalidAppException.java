package com.mybookingsservice.exceptions;

public class InvalidAppException extends AppException {
    public InvalidAppException(String appId) {
        super("Invalid or inactive APPID: " + appId);
    }
}