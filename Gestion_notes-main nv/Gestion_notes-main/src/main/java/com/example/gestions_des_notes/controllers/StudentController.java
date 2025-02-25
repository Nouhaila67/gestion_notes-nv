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
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Display the list of students
    @GetMapping
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students/list";
    }

    // Display the form to create a new student
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/create";
    }

    // Handle the form submission to create a new student
    @PostMapping
    public String createStudent(@ModelAttribute Student student) {
        studentService.createStudent(student);
        return "redirect:/students";
    }

    // Display the form to edit an existing student
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get());
            return "students/edit";
        } else {
            return "redirect:/students";
        }
    }

    // Handle the form submission to update an existing student
    @PostMapping("/{id}/edit")
    public String updateStudent(@PathVariable Long id, @ModelAttribute Student student) {
        studentService.updateStudent(id, student);
        return "redirect:/students";
    }

    // Delete a student
    @GetMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
    // Afficher les détails d'un étudiant
    @GetMapping("/{id}")
    public String getStudentDetails(@PathVariable Long id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isPresent()) {
            model.addAttribute("student", student.get()); // Ajouter l'étudiant au modèle
            return "students/details"; // Retourner la vue Thymeleaf
        } else {
            return "students/student-not-found"; // Rediriger si l'étudiant n'existe pas
        }
    }

}