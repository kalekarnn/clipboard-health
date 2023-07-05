package com.clipboard.healthcare.common;

public enum ErrorCodes {

    ACTIVE_FACILITY_NOT_FOUND(40410001),

    ACTIVE_DOCUMENT_NOT_FOUND(40420001),

    WORKER_NOT_FOUND(40430001),
    WORKER_IS_INACTIVE(40030002),
    OPERATION_FAILED(50000001);


    private final int errorCode;

    ErrorCodes(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
