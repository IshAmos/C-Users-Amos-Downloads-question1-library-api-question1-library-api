package com.example.question1_library_api.controller.student;

import com.example.question1_library_api.model.student.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();

    // Constructor - initialize 5 sample students
    public StudentController() {
        students.add(new Student(1L, "John", "Doe", "john@example.com", "Computer Science", 3.8));
        students.add(new Student(2L, "Jane", "Smith", "jane@example.com", "Computer Science", 3.9));
        students.add(new Student(3L, "Mike", "Johnson", "mike@example.com", "Business", 3.2));
        students.add(new Student(4L, "Sarah", "Williams", "sarah@example.com", "Computer Science", 3.7));
        students.add(new Student(5L, "Tom", "Brown", "tom@example.com", "Biology", 3.1));
    }

    // GET all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // GET student by ID
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET students by major
    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(@PathVariable String major) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getMajor().equalsIgnoreCase(major)) {
                result.add(student);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // GET students by GPA filter
    @GetMapping("/filter")
    public ResponseEntity<List<Student>> filterByGpa(@RequestParam Double gpa) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getGpa() >= gpa) {
                result.add(student);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // POST - Register new student
    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student newStudent) {
        students.add(newStudent);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    // PUT - Update student information
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                student.setFirstName(updatedStudent.getFirstName());
                student.setLastName(updatedStudent.getLastName());
                student.setEmail(updatedStudent.getEmail());
                student.setMajor(updatedStudent.getMajor());
                student.setGpa(updatedStudent.getGpa());
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
