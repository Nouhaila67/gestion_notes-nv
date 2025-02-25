package com.example.gestions_des_notes.security;

import com.example.gestions_des_notes.DAO.StudentRepo;
import com.example.gestions_des_notes.models.Student;
import com.example.gestions_des_notes.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Override
    @Transactional
    public Student createStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        return studentRepo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        return studentRepo.findById(id);
    }

    @Override
    @Transactional
    public boolean deleteStudent(Long id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Student updateStudent(Long id, Student student) {
        Optional<Student> existingStudent = studentRepo.findById(id);
        if (existingStudent.isPresent()) {
            Student updatedStudent = existingStudent.get();
            updatedStudent.setCne(student.getCne());
            updatedStudent.setFirstName(student.getFirstName());
            updatedStudent.setLastName(student.getLastName());
            updatedStudent.setLevel(student.getLevel());
            return studentRepo.save(updatedStudent);
        } else {
            throw new IllegalArgumentException("Student with ID " + id + " not found");
        }
    }


    // Update a student's grade (level)
    public void updateStudentGrade(Long id, String grade) {

    }
}