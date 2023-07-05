package com.clipboard.healthcare.exception;

public class WorkerInactiveException extends RuntimeException {

    public WorkerInactiveException(String message) {
        super(message);
    }
}
