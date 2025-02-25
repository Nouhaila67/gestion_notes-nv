package com.example.gestions_des_notes.service;

import com.example.gestions_des_notes.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student createStudent(Student student);

    List<Student> getAllStudents();

    Optional<Student> getStudentById(Long id);

    boolean deleteStudent(Long id);

    Student updateStudent(Long id, Student student);


    void updateStudentGrade(Long id, String grade);
}