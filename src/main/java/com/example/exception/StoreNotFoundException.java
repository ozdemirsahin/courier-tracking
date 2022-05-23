package com.example.exception;

public class StoreNotFoundException extends RuntimeException{

    public StoreNotFoundException(String message) {
        super(message);
    }

}
