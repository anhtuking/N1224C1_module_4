package com.techzen.academy_n1224c1.service;

import com.techzen.academy_n1224c1.dto.empolyee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEmployeeService {
    Page<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest, Pageable pageable);

    List<Employee> findByNameContaining(String name);

    Employee findById(int id);

    Employee save(Employee employee);

    void delete(int id);
}
