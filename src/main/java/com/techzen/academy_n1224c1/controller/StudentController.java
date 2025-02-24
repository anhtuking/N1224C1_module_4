package com.techzen.academy_n1224c1.controller;

import com.techzen.academy_n1224c1.dto.ApiException;
import com.techzen.academy_n1224c1.dto.ApiResponse;
import com.techzen.academy_n1224c1.exception.ErrorCode;
import com.techzen.academy_n1224c1.model.Student;
import com.techzen.academy_n1224c1.service.IStudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {
    // Cach 1:  @Autowired
//    @Autowired  //DI => tiêm sự phụ thuộc
//    public IStudentService studentService;

    // Cach 2: Constructor
    private final IStudentService studentService;
//    public StudentController(IStudentService studentService) {
//        this.studentService = studentService;
//    }

    // Cach 3: Setter


    @GetMapping
    public ResponseEntity<?> getStudents(@RequestParam(defaultValue = "") String name) {
        return ResponseEntity.ok(ApiResponse.builder()
                .data(studentService.findByName(name))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable("id") int id) {
        Student student = studentService.findById(id);
        if (student == null) {
            throw new ApiException(ErrorCode.STUDENT_NOT_EXIST);
        }
        return ResponseEntity.ok(ApiResponse.<Student>builder()
                .data(student)
                .build());
    }

    @PostMapping
    public ResponseEntity<?> saveStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder()
                .data(studentService.save(student))
                .build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
        student.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder()
                .data(studentService.save(student))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
