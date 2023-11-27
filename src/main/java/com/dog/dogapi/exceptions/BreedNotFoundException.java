package com.dog.dogapi.exceptions;

public class BreedNotFoundException extends RuntimeException {
    public BreedNotFoundException(String message) {
        super(message);
    }
}