package com.techzen.academy_n1224c1.controller;

import com.techzen.academy_n1224c1.dto.ApiException;
import com.techzen.academy_n1224c1.dto.ApiResponse;
import com.techzen.academy_n1224c1.dto.JsonResponse;
import com.techzen.academy_n1224c1.dto.empolyee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.exception.ErrorCode;
import com.techzen.academy_n1224c1.model.Employee;
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

    @GetMapping
    public ResponseEntity<?> getEmployees(EmployeeSearchRequest employeeSearchRequest) {
        return JsonResponse.ok(employeeService.findByAttributes(employeeSearchRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getById(@PathVariable("id") int id) {
        if(employeeService.findById(id) == null) {
            throw new ApiException(ErrorCode.EMPLOYEE_NOT_EXIST);
        }
        return JsonResponse.ok(employeeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        return JsonResponse.created(employeeService.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
        if(employeeService.findById(id) == null) {
            throw new ApiException(ErrorCode.EMPLOYEE_NOT_EXIST);
        }
        employee.setId(id);
        return JsonResponse.ok(employeeService.save(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") int id) {
        if(employeeService.findById(id) == null) {
            throw new ApiException(ErrorCode.EMPLOYEE_NOT_EXIST);
        }
        employeeService.delete(id);
        return JsonResponse.noContent();
    }
}
