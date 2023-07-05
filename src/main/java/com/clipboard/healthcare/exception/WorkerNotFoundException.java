package com.clipboard.healthcare.exception;

public class WorkerNotFoundException extends RuntimeException {

    public WorkerNotFoundException(String message) {
        super(message);
    }
}
