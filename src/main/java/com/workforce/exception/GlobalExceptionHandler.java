package com.workforce.exception;


import com.workforce.support.ApiResponseDto;
import com.workforce.support.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Long generateNonce() {
        return Instant.now().toEpochMilli();
    }

    // Generic exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<Object>> handleException(Exception ex) {
        System.out.println("data already exists-5");
        ApiResponseDto<Object> response = ApiResponseDto.builder()
                .success(false)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .nonce(generateNonce())
                .payload(null)
                .error(ErrorDto.builder().code(ex.toString()).build())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDto<Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        System.out.println("data already exists-4");
        ApiResponseDto<Object> response = ApiResponseDto.builder()
                .success(false)
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .nonce(generateNonce())
                .payload(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        System.out.println("data already exists-3");
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ApiResponseDto<Map<String, String>> response = ApiResponseDto.<Map<String, String>>builder()
                .success(false)
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .nonce(generateNonce())
                .payload(errors)
                .error(ErrorDto.builder().code("Invalid request parameters").build())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    // Data already exists exception
    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<ApiResponseDto<Object>> handleDataAlreadyExists(DataAlreadyExistsException ex) {
        System.out.println("data already exists:");
        ApiResponseDto<Object> response = ApiResponseDto.builder()
                .success(false)
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .nonce(generateNonce())
                .payload(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
