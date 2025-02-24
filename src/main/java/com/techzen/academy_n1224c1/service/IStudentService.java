package com.techzen.academy_n1224c1.service;

import com.techzen.academy_n1224c1.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findByName(String name);

    Student findById(int id);

    Student save(Student student);

    Student delete(int id);
}
