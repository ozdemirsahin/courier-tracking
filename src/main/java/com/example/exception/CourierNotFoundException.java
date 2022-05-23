package com.example.exception;

public class CourierNotFoundException extends RuntimeException{

    public CourierNotFoundException(String message) {
        super(message);
    }

}
