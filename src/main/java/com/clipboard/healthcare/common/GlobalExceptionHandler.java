package com.clipboard.healthcare.common;

import com.clipboard.healthcare.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ActiveFacilityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleActiveFacilityNotFoundException(ActiveFacilityNotFoundException ex, WebRequest request) {

        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorCode(ErrorCodes.ACTIVE_FACILITY_NOT_FOUND.getErrorCode())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ActiveDocumentNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleActiveDocumentNotFoundException(ActiveDocumentNotFoundException ex, WebRequest request) {

        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorCode(ErrorCodes.ACTIVE_DOCUMENT_NOT_FOUND.getErrorCode())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WorkerNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleWorkerNotFoundException(WorkerNotFoundException ex, WebRequest request) {

        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorCode(ErrorCodes.WORKER_NOT_FOUND.getErrorCode())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WorkerInactiveException.class)
    public ResponseEntity<ErrorMessage> handleWorkerInactiveException(WorkerInactiveException ex, WebRequest request) {

        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .errorCode(ErrorCodes.WORKER_IS_INACTIVE.getErrorCode())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({WorkerOperationException.class, DocumentOperationException.class, FacilityOperationException.class, ShiftOperationException.class})
    public ResponseEntity<ErrorMessage> handleOperationException(WorkerInactiveException ex, WebRequest request) {

        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .timestamp(new Date())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorCode(ErrorCodes.OPERATION_FAILED.getErrorCode())
                .description(request.getDescription(false))
                .build();

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
