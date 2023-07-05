package com.clipboard.healthcare.exception;

public class ActiveDocumentNotFoundException extends RuntimeException {

    public ActiveDocumentNotFoundException(String message) {
        super(message);
    }
}
