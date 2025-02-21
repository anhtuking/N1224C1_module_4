package com.techzen.academy_n1224c1.personnel_management;

import com.techzen.academy_n1224c1.dto.ApiException;
import com.techzen.academy_n1224c1.dto.JsonResponse;
import com.techzen.academy_n1224c1.exception.ErrorCode;
import com.techzen.academy_n1224c1.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final List<Employee> employees = new ArrayList<>(
            Arrays.asList(
                    new Employee(UUID.randomUUID(), "Dương Anh Tú", LocalDate.of(2000, 1, 1), Gender.MALE, "0123456789", 100000.000),
                    new Employee(UUID.randomUUID(), "Bành Thị Lỏ", LocalDate.of(2002, 2, 28), Gender.FEMALE, "987654321", 100000.000),
                    new Employee(UUID.randomUUID(), "Đôi Văn Môi", LocalDate.of(1990, 4, 12), Gender.MALE, "024688888", 100000.000),
                    new Employee(UUID.randomUUID(), "David Beck Ben", LocalDate.of(1995, 6, 3), Gender.MALE, "0135799999", 100000.000),
                    new Employee(UUID.randomUUID(), "Tèo Văn Búng", LocalDate.of(1997, 8, 25), Gender.MALE, "0905838688", 100000.000)
            )
    );

    @GetMapping()
    public ResponseEntity<?> getEmployees() {
        return JsonResponse.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") UUID id) {
        for (Employee emp : employees) {
            if (emp.getId().equals(id)) {
                return JsonResponse.ok(employees);
            }
        }
        throw new ApiException(ErrorCode.EMPLOYEE_NOT_EXIST);
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID());
        employees.add(employee);
        return JsonResponse.created(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@PathVariable("id") UUID id, @RequestBody Employee employee) {
        for (Employee emp : employees) {
            if (emp.getId().equals(id)) {
                emp.setName(employee.getName());
                emp.setDob(employee.getDob());
                emp.setGender(employee.getGender());
                emp.setPhone(employee.getPhone());
                emp.setSalary(employee.getSalary());
                return JsonResponse.ok(emp);
            }
        }
        throw new ApiException(ErrorCode.EMPLOYEE_NOT_EXIST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") UUID id) {
        for (Employee emp : employees) {
            if (emp.getId().equals(id)) {
                employees.remove(emp);
                return JsonResponse.noContent();
            }
        }
        throw new ApiException(ErrorCode.EMPLOYEE_NOT_EXIST);
    }
}
