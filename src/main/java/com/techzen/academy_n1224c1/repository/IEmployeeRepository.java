package com.techzen.academy_n1224c1.repository;

import com.techzen.academy_n1224c1.dto.empolyee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.model.Employee;

import java.util.List;

public interface IEmployeeRepository {
    List<Employee> findByAttributes(EmployeeSearchRequest request);

    List<Employee> findByName(String name);

    Employee findByID(int id);

    Employee save(Employee employee);

    Employee delete(int id);
}
