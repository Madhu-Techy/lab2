package com.example.courseapp.exception;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}