package com.amigoscode.SpringSecuirty.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.amigoscode.SpringSecuirty.Entity.Student;
import com.amigoscode.SpringSecuirty.JPARepository.studentJPARepo;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private studentJPARepo studentJPARepo;

    public StudentController(studentJPARepo studentJPARepo) {
        this.studentJPARepo = studentJPARepo;
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentJPARepo.findAll();
    }

    @GetMapping(path = "/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        return studentJPARepo.findById(studentId).get();

    }

}
