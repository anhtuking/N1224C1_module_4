package com.techzen.academy_n1224c1.service;

import com.techzen.academy_n1224c1.dto.empolyee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.model.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEmployeeService {
    List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest);

    List<Employee> findByNameContaining(String name);

    Employee findById(int id);

    Employee save(Employee employee);

    void delete(int id);
}
