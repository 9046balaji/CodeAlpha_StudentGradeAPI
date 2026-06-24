package com.codealpha.gradeapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Subject is required")
    private String subject;

    @Min(value = 0, message = "Score must not be less than 0")
    @Max(value = 100, message = "Score must not be greater than 100")
    private int score;

    private String letterGrade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    // Constructors
    public Grade() {}

    public Grade(String subject, int score) {
        this.subject = subject;
        this.score = score;
        calculateLetterGrade();
    }

    // Custom method to calculate letter grade before saving
    @PrePersist
    @PreUpdate
    public void calculateLetterGrade() {
        if (score >= 90) this.letterGrade = "A";
        else if (score >= 80) this.letterGrade = "B";
        else if (score >= 70) this.letterGrade = "C";
        else if (score >= 60) this.letterGrade = "D";
        else this.letterGrade = "F";
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public int getScore() { return score; }
    public void setScore(int score) { 
        this.score = score; 
        calculateLetterGrade();
    }

    public String getLetterGrade() { return letterGrade; }
    public void setLetterGrade(String letterGrade) { this.letterGrade = letterGrade; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
}
