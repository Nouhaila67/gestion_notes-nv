package com.example.gestions_des_notes.controllers;

import com.example.gestions_des_notes.models.Student;
import com.example.gestions_des_notes.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    // Display the list of students with their grades
    @GetMapping("/students")
    public String showAdminPage(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students); // Add the list of students to the model
        return "admin/admin-notes"; // Return the Thymeleaf view
    }

    // Display the form to edit a student's grade
    @GetMapping("/students/{id}/edit-grade")
    public String showEditGradeForm(@PathVariable Long id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get()); // Add the student to the model
            return "admin/edit-grade"; // Return the Thymeleaf view
        } else {
            return "redirect:/admin/students"; // Redirect if the student does not exist
        }
    }

    // Update a student's grade
    @PostMapping("/students/{id}/edit-grade")
    public String updateGrade(@PathVariable Long id, @RequestParam String grade) {
        studentService.updateStudentGrade(id, grade); // Update the grade
        return "redirect:/admin/students"; // Redirect to the list of students
    }
}