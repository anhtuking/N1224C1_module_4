package com.techzen.academy_n1224c1.service.impl;

import com.techzen.academy_n1224c1.dto.empolyee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.model.Employee;
import com.techzen.academy_n1224c1.model.Student;
import com.techzen.academy_n1224c1.repository.impl.EmployeeRepository;
import com.techzen.academy_n1224c1.repository.impl.StudentRepository;
import com.techzen.academy_n1224c1.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService implements IEmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest) {
        return employeeRepository.findByAttributes(employeeSearchRequest);
    }

    public List<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    public Employee findByID(int id) {
        return employeeRepository.findByID(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee delete(int id) {
        return employeeRepository.delete(id);
    }
    }
