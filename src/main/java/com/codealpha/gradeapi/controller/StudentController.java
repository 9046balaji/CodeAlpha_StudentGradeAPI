package com.codealpha.gradeapi.controller;

import com.codealpha.gradeapi.model.Grade;
import com.codealpha.gradeapi.model.Student;
import com.codealpha.gradeapi.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.CREATED);
    }

    @PostMapping("/{studentId}/grades")
    public ResponseEntity<Grade> addGrade(
            @PathVariable Long studentId, 
            @Valid @RequestBody Grade grade) {
        return new ResponseEntity<>(studentService.addGradeToStudent(studentId, grade), HttpStatus.CREATED);
    }

    @GetMapping("/{studentId}/grades")
    public ResponseEntity<List<Grade>> getStudentGrades(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.getStudentGrades(studentId));
    }
}
