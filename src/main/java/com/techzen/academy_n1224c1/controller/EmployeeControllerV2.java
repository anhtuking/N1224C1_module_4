package com.techzen.academy_n1224c1.controller;

import com.techzen.academy_n1224c1.dto.ApiException;
import com.techzen.academy_n1224c1.dto.ApiResponse;
import com.techzen.academy_n1224c1.dto.JsonResponse;
import com.techzen.academy_n1224c1.dto.empolyee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.exception.ErrorCode;
import com.techzen.academy_n1224c1.model.Employee;
import com.techzen.academy_n1224c1.model.Student;
import com.techzen.academy_n1224c1.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeControllerV2 {
    IEmployeeService employeeService;

//    @GetMapping
//    public ResponseEntity<?> getEmployees(EmployeeSearchRequest employeeSearchRequest) {
//        return JsonResponse.ok(employeeService.findByAttributes(employeeSearchRequest));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getById(@PathVariable("id") int id) {
        Employee employee = employeeService.findByID(id);
        if (employee == null) {
            throw new ApiException(ErrorCode.EMPLOYEE_NOT_EXIST);
        }
        return ResponseEntity.ok(ApiResponse.<Employee>builder()
                .data(employee)
                .build());
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder()
                .data(employeeService.save(employee))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
        employee.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                .data(employeeService.save(employee))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") int id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
