package com.test.project.exception;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.test.project.bean.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeExceptions(RuntimeException ex) {
        String error = "Internal Server Error";
        String errorCode = "GEN_001";
        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();

        // Duplicate email
        if (ex instanceof DuplicateEmailException) {
            error = "Duplicate Email";
            errorCode = "USR_001";
            status = HttpStatus.CONFLICT.value();
        }

        // other DB integrity issues
        if (ex instanceof DataIntegrityViolationException) {
            error = "Database Error";
            errorCode = "DB_001";
            status = HttpStatus.CONFLICT.value();
        }

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status,
                error,
                ex.getMessage(),
                errorCode
        );

        return ResponseEntity.status(status).body(errorResponse);
    }
}
