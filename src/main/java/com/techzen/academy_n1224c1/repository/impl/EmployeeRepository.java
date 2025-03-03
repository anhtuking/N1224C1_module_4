package com.techzen.academy_n1224c1.repository.impl;

import com.techzen.academy_n1224c1.dto.ApiException;
import com.techzen.academy_n1224c1.dto.empolyee.EmployeeSearchRequest;
import com.techzen.academy_n1224c1.exception.ErrorCode;
import com.techzen.academy_n1224c1.model.Employee;
import com.techzen.academy_n1224c1.enums.Gender;
import com.techzen.academy_n1224c1.repository.IEmployeeRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeRepository implements IEmployeeRepository {

    public List<Employee> findByAttributes(EmployeeSearchRequest request) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        String str = "from Employee e left join fetch e.department where "
                + "(:name is null or lower(e.name) like concat('%', :name, '%')) "
                + "and (:dobFrom is null or e.dob >= :dobFrom) "
                + "and (:dobTo is null or e.dob <= :dobTo) "
                + "and (:gender is null or e.gender = :gender) "
                + "and (:phone is null or e.phone like concat('%', :phone, '%')) "
                + "and (:departmentId is null or e.department.id = :departmentId) ";

        if (request.getSalaryRange() != null) {
            str += "and (";
            switch (request.getSalaryRange()) {
                case "lt5":
                    str += "e.salary < 5000000 ";
                    break;
                case "5-10":
                    str += "e.salary >= 5000000 and e.salary < 10000000 ";
                    break;
                case "10-20":
                    str += "e.salary >= 10000000 and e.salary <= 20000000 ";
                    break;
                case "gt20":
                    str += "e.salary > 20000000 ";
                    break;
            }
            str += ") ";
        }
        Query<Employee> query = session.createQuery(str, Employee.class);

        query.setParameter("name", request.getName());
        query.setParameter("dobFrom", request.getDobFrom());
        query.setParameter("dobTo", request.getDobTo());
        query.setParameter("gender", request.getGender());
        query.setParameter("phone", request.getPhone());
        query.setParameter("departmentId", request.getDepartmentId());

        return query.getResultList();
    }

    public List<Employee> findByName(String name) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        List<Employee> employees = null;
        try {
            employees = session.createQuery("from Employee where name like concat('%', :name, '%') ")
                    .setParameter("name", name)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

    public Employee findByID(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Employee employee = null;
        try {
            employee = (Employee) session.createQuery("from Employee where id = :id")
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employee;
    }
    @Override
    public Employee save(Employee employee) {
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try{
                session.saveOrUpdate(employee);
                transaction.commit();
            } catch (Exception e) {
                if(transaction.isActive()){
                    transaction.rollback();
                }
                throw new RuntimeException(e);
            }
        }
        throw new ApiException(ErrorCode.EMPLOYEE_NOT_EXIST);
    }
    @Override
    public Employee delete(int id) {
        try {
            // Truy vấn db
            PreparedStatement preparedStatement = BaseRepository.getConnection()
                    .prepareStatement("delete from Employee where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new ApiException(ErrorCode.EMPLOYEE_NOT_EXIST);
    }
}
