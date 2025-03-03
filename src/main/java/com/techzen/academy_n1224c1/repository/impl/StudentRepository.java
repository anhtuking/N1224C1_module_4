package com.techzen.academy_n1224c1.repository.impl;

import com.techzen.academy_n1224c1.dto.ApiException;
import com.techzen.academy_n1224c1.exception.ErrorCode;
import com.techzen.academy_n1224c1.model.Student;
import com.techzen.academy_n1224c1.repository.IStudentRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository {
    public List<Student> findByName(String name) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        List<Student> students = null;
        try {
            students = session.createQuery("FROM Student WHERE name like concat('%', :name, '%') ")
                    .setParameter("name", name)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return students;
    }

    public Student findByID(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Student student = null;
        try {
            student = (Student) session.createQuery("FROM Student WHERE id = :id")
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return student;
    }

    public Student save(Student student) {
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try{
                session.saveOrUpdate(student);
                transaction.commit();
            } catch (Exception e) {
                if(transaction.isActive()){
                    transaction.rollback();
                }
                throw new RuntimeException(e);
            }
        }
        return student;
    }

    public Student delete(int id) {
        try {
            // Truy vấn db
            PreparedStatement preparedStatement = BaseRepository.getConnection()
                    .prepareStatement("delete from student where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
