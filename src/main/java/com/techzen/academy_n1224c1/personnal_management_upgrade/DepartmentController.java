package com.techzen.academy_n1224c1.personnal_management_upgrade;

import com.techzen.academy_n1224c1.dto.ApiException;
import com.techzen.academy_n1224c1.dto.JsonResponse;
import com.techzen.academy_n1224c1.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final List<Department> departments = new ArrayList<>(
            Arrays.asList(
                    new Department(1, "Manage"),
                    new Department(2, "Accountant"),
                    new Department(3, "Sale-Marketing"),
                    new Department(4, "Manufacture")
            )
    );

    @GetMapping
    public ResponseEntity<?> getAllDepartments() {
        return JsonResponse.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable("id") int id) {
        for (Department department : departments) {
            if (department.getId() == id) {
                return JsonResponse.ok(department);
            }
        }
        throw new ApiException(ErrorCode.DEPARTMENT_NOT_EXIST);
    }

    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        department.setId((int) (Math.random()*1000000));
        departments.add(department);
        return JsonResponse.created(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartmentById(@PathVariable("id") int id, @RequestBody Department department) {
        for (Department department1 : departments) {
            if (department1.getId() == id) {
                department1.setName(department.getName());
                return JsonResponse.ok(department1);
            }
        }
        throw new ApiException(ErrorCode.DEPARTMENT_NOT_EXIST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartmentById(@PathVariable("id") int id) {
        for (Department department : departments) {
            if (department.getId() == id) {
                departments.remove(department);
                return JsonResponse.ok(department);
            }
        }
        throw new ApiException(ErrorCode.DEPARTMENT_NOT_EXIST);
    }
}
