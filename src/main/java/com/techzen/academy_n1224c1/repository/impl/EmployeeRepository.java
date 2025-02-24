package com.techzen.academy_n1224c1.repository.impl;

import com.techzen.academy_n1224c1.dto.ApiException;
import com.techzen.academy_n1224c1.dto.empolyee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.exception.ErrorCode;
import com.techzen.academy_n1224c1.model.Employee;
import com.techzen.academy_n1224c1.enums.Gender;
import com.techzen.academy_n1224c1.repository.IEmployeeRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeRepository implements IEmployeeRepository {
    List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee(UUID.randomUUID(), "Dương Anh Tú", LocalDate.of(2000, 1, 1), Gender.MALE, "0123456789", 10000000, 1),
            new Employee(UUID.randomUUID(), "Bành Thị Lỏ", LocalDate.of(2002, 2, 28), Gender.FEMALE, "987654321", 100000.000, 2),
            new Employee(UUID.randomUUID(), "Đôi Văn Môi", LocalDate.of(1990, 4, 12), Gender.MALE, "024688888", 100000.000, 3),
            new Employee(UUID.randomUUID(), "David Beck Ben", LocalDate.of(1995, 6, 3), Gender.MALE, "0135799999", 100000.000, 4),
            new Employee(UUID.randomUUID(), "Tèo Văn Búng", LocalDate.of(1997, 8, 25), Gender.MALE, "0905838688", 100000.000, 5)
    ));
    @Override
    public List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest) {
        return employees.stream()
                .filter(e -> (employeeSearchRequest.getName() == null || e.getName().toLowerCase().contains(employeeSearchRequest.getName().toLowerCase())))
                .filter(e -> (employeeSearchRequest.getDobFrom() == null || !e.getDob().isBefore(employeeSearchRequest.getDobFrom())))
                .filter(e -> (employeeSearchRequest.getDobTo() == null || !e.getDob().isAfter(employeeSearchRequest.getDobTo())))
                .filter(e -> (employeeSearchRequest.getGender() == null || e.getGender() == employeeSearchRequest.getGender()))
                .filter(e -> (employeeSearchRequest.getPhone() == null || e.getPhone().contains(employeeSearchRequest.getPhone())))
                .filter(e -> (employeeSearchRequest.getDepartmentId() == null || Objects.equals(e.getDepartmentId(), employeeSearchRequest.getDepartmentId())))
                .filter(e -> {
                    if (employeeSearchRequest.getSalaryRange() == null) {
                        return true;
                    }
                    return switch (employeeSearchRequest.getSalaryRange()) {
                        case "lt5" -> e.getSalary() < 5000000;
                        case "5-10" -> e.getSalary() >= 5000000 && e.getSalary() < 10000000;
                        case "10-20" -> e.getSalary() >= 10000000 && e.getSalary() <= 20000000;
                        case "gt20" -> e.getSalary() > 20000000;
                        default -> true;
                    };
                })
                .collect(Collectors.toList());
    }
    @Override
    public Optional<Employee> findByID(UUID id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
    }
    @Override
    public Employee save(Employee employee) {
        for (Employee emp : employees) {
            if (emp.getId() == employee.getId()){
                emp.setName(employee.getName());
                emp.setDob(employee.getDob());
                emp.setGender(employee.getGender());
                emp.setPhone(employee.getPhone());
                emp.setSalary(employee.getSalary());
                return emp;
            }
        }
        employee.setId(UUID.randomUUID());
        employees.add(employee);
        return employee;
    }
    @Override
    public void delete(UUID id) {
        findByID(id).ifPresent(employees::remove);
    }
}
