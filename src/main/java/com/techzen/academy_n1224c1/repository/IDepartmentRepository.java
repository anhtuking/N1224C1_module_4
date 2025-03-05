package com.techzen.academy_n1224c1.repository;

import com.techzen.academy_n1224c1.model.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDepartmentRepository  extends JpaRepository<Department, Integer> {
    @Query("FROM Department ")
    public List<Department> getAll();

    List<Department> findByNameContaining(String name);

    Department findById(int id);
}
