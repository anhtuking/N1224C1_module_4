package com.techzen.academy_n1224c1.exception;

import com.techzen.academy_n1224c1.dto.ApiException;
import com.techzen.academy_n1224c1.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExeptionHandle {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> hanldApiException(ApiException e){
        ErrorCode errorCode = e.getErrorCode();
        
        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build()
                );
    }
}
