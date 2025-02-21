package com.techzen.academy_n1224c1.personnal_management_upgrade;

import com.techzen.academy_n1224c1.dto.ApiException;
import com.techzen.academy_n1224c1.dto.ApiResponse;
import com.techzen.academy_n1224c1.dto.JsonResponse;
import com.techzen.academy_n1224c1.exception.ErrorCode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeControllerV2 {
    private final List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee(UUID.randomUUID(), "Dương Anh Tú", LocalDate.of(2000, 1, 1), Gender.MALE, "0123456789", 10000000, 1),
            new Employee(UUID.randomUUID(), "Bành Thị Lỏ", LocalDate.of(2002, 2, 28), Gender.FEMALE, "987654321", 100000.000, 2),
            new Employee(UUID.randomUUID(), "Đôi Văn Môi", LocalDate.of(1990, 4, 12), Gender.MALE, "024688888", 100000.000, 3),
            new Employee(UUID.randomUUID(), "David Beck Ben", LocalDate.of(1995, 6, 3), Gender.MALE, "0135799999", 100000.000, 4),
            new Employee(UUID.randomUUID(), "Tèo Văn Búng", LocalDate.of(1997, 8, 25), Gender.MALE, "0905838688", 100000.000, 5)
    ));

    @GetMapping
    public ResponseEntity<?> getEmployees(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "dobFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobFrom,
            @RequestParam(value = "dobTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dobTo,
            @RequestParam(value = "gender", required = false) Gender gender,
            @RequestParam(value = "salaryRange", required = false) String salaryRange,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "departmentId", required = false) Integer departmentId
    ) {
        List<Employee> filteredEmployees = employees.stream()
                .filter(e -> (name == null || e.getName().toLowerCase().contains(name.toLowerCase())))
                .filter(e -> (dobFrom == null || !e.getDob().isBefore(dobFrom)))
                .filter(e -> (dobTo == null || !e.getDob().isAfter(dobTo)))
                .filter(e -> (gender == null || e.getGender() == gender))
                .filter(e -> (phone == null || e.getPhone().contains(phone)))
                .filter(e -> (departmentId == null || Objects.equals(e.getDepartmentId(), departmentId)))
                .filter(e -> {
                    if (salaryRange == null) {
                        return true;
                    }
                    return switch (salaryRange) {
                        case "lt5" -> e.getSalary() < 5000000;
                        case "5-10" -> e.getSalary() >= 5000000 && e.getSalary() < 10000000;
                        case "10-20" -> e.getSalary() >= 10000000 && e.getSalary() <= 20000000;
                        case "gt20" -> e.getSalary() > 20000000;
                        default -> true;
                    };
                })
                .collect(Collectors.toList());
        return JsonResponse.ok(filteredEmployees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") UUID id) {
        for (Employee emp : employees) {
            if (emp.getId().equals(id)) {
                return JsonResponse.ok(emp);
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
