package com.lakshman.springmvc_json.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lakshman.springmvc_json.dto.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleResourceNotFoundException(
            ResourceNotFoundException ex) {

        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setData(null);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> handleRuntimeException(
            RuntimeException ex) {

        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setData(null);
        apiResponse.setSuccess(false);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}