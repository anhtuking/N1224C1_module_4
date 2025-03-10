package com.techzen.academy_n1224c1.repository.impl;

import com.techzen.academy_n1224c1.enums.Gender;
import com.techzen.academy_n1224c1.model.Employee;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class EmployeeSpecification {

    public static Specification<Employee> hasName(String name) {
        return (root, query, cb) -> name == null ? null :
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Employee> hasDobFrom(LocalDate dobFrom) {
        return (root, query, cb) -> dobFrom == null ? null :
                cb.greaterThanOrEqualTo(root.get("dob"), dobFrom);
    }

    public static Specification<Employee> hasDobTo(LocalDate dobTo) {
        return (root, query, cb) -> dobTo == null ? null :
                cb.lessThanOrEqualTo(root.get("dob"), dobTo);
    }

    public static Specification<Employee> hasGender(Gender gender) {
        return (root, query, cb) -> gender == null ? null :
                cb.equal(root.get("gender"), gender);
    }

    public static Specification<Employee> hasPhone(String phone) {
        return (root, query, cb) -> phone == null ? null :
                cb.like(root.get("phone"), "%" + phone + "%");
    }

    public static Specification<Employee> hasDepartmentId(Integer departmentId) {
        return (root, query, cb) -> departmentId == null ? null :
                cb.equal(root.join("department", JoinType.LEFT).get("id"), departmentId);
    }

    public static Specification<Employee> hasSalaryInRange(String salaryRange) {
        return (root, query, cb) -> {
            if (salaryRange == null) return null;
            switch (salaryRange) {
                case "lt5":
                    return cb.lessThan(root.get("salary"), 5000000);
                case "5-10":
                    return cb.between(root.get("salary"), 5000000, 10000000);
                case "10-20":
                    return cb.between(root.get("salary"), 10000000, 20000000);
                case "gt20":
                    return cb.greaterThan(root.get("salary"), 20000000);
                default:
                    return null;
            }
        };
    }
}
