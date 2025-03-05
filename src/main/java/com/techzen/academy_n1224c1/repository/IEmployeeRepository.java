package com.techzen.academy_n1224c1.repository;

import com.techzen.academy_n1224c1.dto.empolyee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = """
        SELECT e.* FROM employee e
        LEFT JOIN department d ON e.department_id = d.id
        WHERE (:#{#searchRequest.name} IS NULL OR e.name LIKE CONCAT('%', :#{#searchRequest.name}, '%'))
        AND (:#{#searchRequest.dobFrom} IS NULL OR e.dob >= :#{#searchRequest.dobFrom})
        AND (:#{#searchRequest.dobTo} IS NULL OR e.dob <= :#{#searchRequest.dobTo})
        AND (:#{#searchRequest.gender} IS NULL OR e.gender = :#{#searchRequest.gender})
        AND (:#{#searchRequest.phone} IS NULL OR e.phone LIKE CONCAT('%', :#{#searchRequest.phone}, '%'))
        AND (:#{#searchRequest.departmentId} IS NULL OR d.id = :#{#searchRequest.departmentId})
        AND (
        CASE
        WHEN :#{#searchRequest.salaryRange} = '1-5' THEN e.salary < 5000000
        WHEN :#{#searchRequest.salaryRange} = '5-10' THEN e.salary BETWEEN 5000000 AND 10000000
        WHEN :#{#searchRequest.salaryRange} = '10-20' THEN e.salary BETWEEN 10000000 AND 20000000
        WHEN :#{#searchRequest.salaryRange} = 'gt20' THEN e.salary > 20000000
        ELSE TRUE
        END
        )
        """, nativeQuery = true)
    List<Employee> findByAttributes(@Param("searchRequest") EmployeeSearchRequest searchRequest);

    List<Employee> findByNameContaining(String name);

    Employee findById(int id);
}
