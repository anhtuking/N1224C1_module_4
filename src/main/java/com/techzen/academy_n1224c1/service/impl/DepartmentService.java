package com.techzen.academy_n1224c1.service.impl;

import com.techzen.academy_n1224c1.model.Department;
import com.techzen.academy_n1224c1.model.Employee;
import com.techzen.academy_n1224c1.repository.IDepartmentRepository;
import com.techzen.academy_n1224c1.service.IDepartmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentService implements IDepartmentService {
    private final IDepartmentRepository departmentRepository;
    public List<?> getAll() {
        return departmentRepository.getAll();
    }

    public List<Department> findByNameContaining(String name) {
        return departmentRepository.findByNameContaining(name);
    }

    public Department findById(int id) {
        return departmentRepository.findById(id);
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public void delete(int id) {
        departmentRepository.delete(findById(id));
    }
}

