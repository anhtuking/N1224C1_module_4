package com.techzen.academy_n1224c1.repository;

import com.techzen.academy_n1224c1.dto.empolyee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = """
            SELECT e.* FROM employee e
            LEFT JOIN department d ON e.id_department_id = d.id
            WHERE (:name IS NULL OR e.name LIKE CONCAT('%', :name, '%'))
            AND (:dobFrom IS NULL OR e.birth >= :dobFrom)
            AND (:dobTo IS NULL OR e.birth <= :dobTo)
            AND (:gender IS NULL OR e.gender = :gender)
            AND (:phone IS NULL OR e.phone LIKE CONCAT('%', :phone, '%'))
            AND (:departmentId IS NULL OR d.id = :departmentId)
            AND (
                CASE
                    WHEN :salaryRange = 'lt5' THEN e.salary < 5000000
                    WHEN :salaryRange = '5-10' THEN e.salary BETWEEN 5000000 AND 10000000
                    WHEN :salaryRange = '10-20' THEN e.salary BETWEEN 10000000 AND 20000000
                    WHEN :salaryRange = 'gt20' THEN e.salary > 20000000
                    ELSE TRUE
                END
            )
            """, nativeQuery = true)
    List<Employee> findByAttributes(@Param("searchRequest") EmployeeSearchRequest searchRequest);

    List<Employee> findByNameContaining(String name);

    Employee findById(int id);
}
