package com.reka.lakatos.angularchatbackend.exception;

public class AppUserNotFoundException extends RuntimeException {
    public AppUserNotFoundException(String message) {
        super(message);
    }
}
