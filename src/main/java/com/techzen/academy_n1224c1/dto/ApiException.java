package com.techzen.academy_n1224c1.dto;

import com.techzen.academy_n1224c1.exception.ErrorCode;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiException extends RuntimeException {
    ErrorCode errorCode;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
