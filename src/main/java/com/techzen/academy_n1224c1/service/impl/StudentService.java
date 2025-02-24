package com.techzen.academy_n1224c1.service.impl;

import com.techzen.academy_n1224c1.model.Student;
import com.techzen.academy_n1224c1.repository.impl.StudentRepository;
import com.techzen.academy_n1224c1.service.IStudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;

    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    public Student findById(int id) {
        return studentRepository.findByID(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student delete(int id) {
        return studentRepository.delete(id);
    }
}
