package com.example.exception;

public class CourierExistException extends RuntimeException{

    public CourierExistException(String message) {
        super(message);
    }

}
