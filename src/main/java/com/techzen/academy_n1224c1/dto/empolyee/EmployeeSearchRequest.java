package com.techzen.academy_n1224c1.dto.empolyee;

import com.techzen.academy_n1224c1.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeSearchRequest {
    String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE);
    LocalDate dobFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE);
    LocalDate dobTo;

    Gender gender;
    String salaryRange;
    String phone;
    Integer departmentId;
}
