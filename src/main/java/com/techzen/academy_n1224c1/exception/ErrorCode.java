package com.techzen.academy_n1224c1.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    STUDENT_NOT_EXIST(404,"Student is not exist", HttpStatus.NOT_FOUND),
    EMPLOYEE_NOT_EXIST(404,"Employee is not exist", HttpStatus.NOT_FOUND),
    DEPARTMENT_NOT_EXIST(404,"Department is not exist", HttpStatus.NOT_FOUND),
    MAT_BANG_NOT_EXIST(404,"Mat bang is not exist", HttpStatus.NOT_FOUND)
    ;
    int code;
    String message;
    HttpStatus status;
}
