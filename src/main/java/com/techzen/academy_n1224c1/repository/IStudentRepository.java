package com.techzen.academy_n1224c1.repository;

import com.techzen.academy_n1224c1.model.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> findByName(String name);
    Student findByID(int id);
    Student save(Student student);
    Student delete(int id);
}
