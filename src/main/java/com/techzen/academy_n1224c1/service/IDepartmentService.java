package com.techzen.academy_n1224c1.service;

import com.techzen.academy_n1224c1.model.Department;

import java.util.List;

public interface IDepartmentService {
    public List<?> getAll();

    List<Department> findByNameContaining(String name);

    public Department findById(int id);

    Department save(Department department);

    void delete(int id);
}
