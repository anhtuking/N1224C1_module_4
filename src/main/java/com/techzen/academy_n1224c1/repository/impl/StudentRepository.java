package com.techzen.academy_n1224c1.repository.impl;

import com.techzen.academy_n1224c1.dto.ApiException;
import com.techzen.academy_n1224c1.exception.ErrorCode;
import com.techzen.academy_n1224c1.model.Student;
import com.techzen.academy_n1224c1.repository.IStudentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository {
    private List<Student> students = new ArrayList<Student>(
            Arrays.asList(
                    new Student(1, "Tu", 10),
                    new Student(2, "Tuan", 10),
                    new Student(3, "Nhan", 10)
            )
    );

    public List<Student> findByName(String name) {
        List<Student> studentSearch = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().contains(name)) {
                studentSearch.add(student);
            }
        }
        return studentSearch;
    }

    public Student findByID(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public Student save(Student student) {
        for (Student stud : students) {
            if (stud.getId() == student.getId()) {
                stud.setName(student.getName());
                stud.setScore(student.getScore());
                return stud;
            }
        }

        student.setId((int) (Math.random() * 10000));
        students.add(student);
        return student;
    }

    public Student delete(int id) {
        for (Student stud : students) {
            if (stud.getId() == id) {
                students.remove(stud);
                return stud;
            }
        }
        throw new ApiException(ErrorCode.STUDENT_NOT_EXIST);
    }
}
