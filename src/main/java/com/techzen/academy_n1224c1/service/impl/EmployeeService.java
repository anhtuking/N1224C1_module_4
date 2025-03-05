package com.techzen.academy_n1224c1.service.impl;

import com.techzen.academy_n1224c1.dto.empolyee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.model.Employee;
import com.techzen.academy_n1224c1.repository.IEmployeeRepository;
import com.techzen.academy_n1224c1.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService implements IEmployeeService {
    private final IEmployeeRepository employeeRepository;

    public List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest) {
        return employeeRepository.findByAttributes(employeeSearchRequest);
    }

    public List<Employee> findByNameContaining(String name) {
        return employeeRepository.findByNameContaining(name);
    }

    public Employee findById(int id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void delete(int id) {
        employeeRepository.delete(findById(id));
    }
    }
