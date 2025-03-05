package com.techzen.academy_n1224c1.controller;

import com.techzen.academy_n1224c1.dto.ApiException;
import com.techzen.academy_n1224c1.dto.ApiResponse;
import com.techzen.academy_n1224c1.dto.JsonResponse;
import com.techzen.academy_n1224c1.exception.ErrorCode;
import com.techzen.academy_n1224c1.model.Department;
import com.techzen.academy_n1224c1.service.IDepartmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentController {
    IDepartmentService departmentService;

    @GetMapping
    private ResponseEntity<?> getAll() {
        return JsonResponse.ok(departmentService.getAll());
    }

//    @GetMapping
//    public ResponseEntity<?> getDepartmentByName(@RequestParam(defaultValue = "") String name) {
//        return ResponseEntity.ok(departmentService.findByNameContaining(name));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable("id") int id) {
        if(departmentService.findById(id) == null) {
            throw new ApiException(ErrorCode.DEPARTMENT_NOT_EXIST);
        }
        return JsonResponse.ok(departmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        return JsonResponse.created(departmentService.save(department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartmentById(@PathVariable("id") int id, @RequestBody Department department) {
        if(departmentService.findById(id) == null) {
            throw new ApiException(ErrorCode.DEPARTMENT_NOT_EXIST);
        }
        department.setId(id);
        return JsonResponse.ok(departmentService.save(department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartmentById(@PathVariable("id") int id) {
        if(departmentService.findById(id) == null) {
            throw new ApiException(ErrorCode.DEPARTMENT_NOT_EXIST);
        }
        departmentService.delete(id);
        return JsonResponse.noContent();
    }
}
