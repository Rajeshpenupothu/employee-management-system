package com.example.ems.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException exception) {

        ErrorResponse errorResponse =
                new ErrorResponse(
                        "RESOURCE_NOT_FOUND",
                        exception.getMessage(),
                        LocalDateTime.now());

        return new ResponseEntity<>(
                errorResponse,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(
            RuntimeException exception) {

        ErrorResponse errorResponse =
                new ErrorResponse(
                        "BUSINESS_RULE_VIOLATION",
                        exception.getMessage(),
                        LocalDateTime.now());

        return new ResponseEntity<>(
                errorResponse,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception exception) {

        ErrorResponse errorResponse =
                new ErrorResponse(
                        "INTERNAL_SERVER_ERROR",
                        exception.getMessage(),
                        LocalDateTime.now());

        return new ResponseEntity<>(
                errorResponse,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}