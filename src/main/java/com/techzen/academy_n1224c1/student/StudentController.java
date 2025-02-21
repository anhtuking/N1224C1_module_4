package com.techzen.academy_n1224c1.student;

import com.techzen.academy_n1224c1.dto.ApiException;
import com.techzen.academy_n1224c1.dto.ApiResponse;
import com.techzen.academy_n1224c1.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private List<Student> students = new ArrayList<Student>(
            Arrays.asList(
                    new Student(1, "Tu", 10),
                    new Student(2, "Tuan", 10),
                    new Student(3, "Nhan", 10)
            )
    );
    @GetMapping
    public List<Student> getStudents(@RequestParam(defaultValue = "") String name) {
        List<Student> searchStudents = new ArrayList<>();
                for(Student s : students){
                    if(s.getName().contains(name)){
                        searchStudents.add(s);
                    }
        }
        return searchStudents;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable("id") int id) {
        for(Student s : students){
            if(s.getId() == id){
                return ResponseEntity.ok(com.techzen.academy_n1224c1.dto.ApiResponse.<Student>builder()
                                .data(s)
                                .build());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                com.techzen.academy_n1224c1.dto.ApiResponse.<Student>builder()
                        .code(404)
                        .message("Student is not exist")
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        student.setId((int) (Math.random() * 100));
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") int id, @RequestBody Student student){

        for (Student s1 : students) {
            if(s1.getId() == id){
                s1.setName(student.getName());
                s1.setScore(student.getScore());
                return ResponseEntity.ok(com.techzen.academy_n1224c1.dto.ApiResponse.<Student>builder()
                        .data(s1)
                        .build());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                com.techzen.academy_n1224c1.dto.ApiResponse.<Student>builder()
                        .code(404)
                        .message("Student is not exist")
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
        for (Student s2 : students) {
            if(s2.getId() == id){
                students.remove(s2);
                return ResponseEntity.ok(com.techzen.academy_n1224c1.dto.ApiResponse.<Student>builder()
                        .data(s2)
                        .build());
            }
        }
        throw new ApiException(ErrorCode.STUDENT_NOT_EXIST);
    }
}
