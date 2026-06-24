package com.codealpha.gradeapi.service;

import com.codealpha.gradeapi.model.Grade;
import com.codealpha.gradeapi.model.Student;
import com.codealpha.gradeapi.repository.GradeRepository;
import com.codealpha.gradeapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Grade addGradeToStudent(Long studentId, Grade grade) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found"));
        
        student.addGrade(grade);
        return gradeRepository.save(grade);
    }

    public List<Grade> getStudentGrades(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }
}
