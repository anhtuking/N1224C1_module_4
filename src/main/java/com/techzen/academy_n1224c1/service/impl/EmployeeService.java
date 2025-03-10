package com.techzen.academy_n1224c1.service.impl;

import com.techzen.academy_n1224c1.dto.empolyee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.model.Employee;
import com.techzen.academy_n1224c1.repository.IEmployeeRepository;
import com.techzen.academy_n1224c1.repository.impl.EmployeeSpecification;
import com.techzen.academy_n1224c1.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService implements IEmployeeService {
    private final IEmployeeRepository employeeRepository;

//    public Page<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest, Pageable pageable) {
//        return employeeRepository.findByAttributes(employeeSearchRequest, pageable);
//    }

    @Override
    public Page<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest, Pageable pageable) {
        Specification<Employee> specification = Specification.where(EmployeeSpecification.hasName(employeeSearchRequest.getName()))
                .and(EmployeeSpecification.hasDobFrom(employeeSearchRequest.getDobFrom()))
                .and(EmployeeSpecification.hasDobTo(employeeSearchRequest.getDobTo()))
                .and(EmployeeSpecification.hasGender(employeeSearchRequest.getGender()))
                .and(EmployeeSpecification.hasPhone(employeeSearchRequest.getPhone()))
                .and(EmployeeSpecification.hasDepartmentId(employeeSearchRequest.getDepartmentId()))
                .and(EmployeeSpecification.hasSalaryInRange(employeeSearchRequest.getSalaryRange()));

        return employeeRepository.findAll(specification, pageable);
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
