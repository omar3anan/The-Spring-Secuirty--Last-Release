package com.amigoscode.SpringSecuirty.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amigoscode.SpringSecuirty.Entity.Student;
import com.amigoscode.SpringSecuirty.JPARepository.studentJPARepo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//! Law hatetsht8al as Backend Developer w te3ml APIs w Front react

@RestController
@RequestMapping("/management/v1/students")
public class StudentManagmentController {
    private studentJPARepo studentJPARepo;

    public StudentManagmentController(studentJPARepo studentJPARepo) {
        this.studentJPARepo = studentJPARepo;
    }

    // @PreAuthorize hasRole, hasAnyRole, hasAuthority, hasAnyAuthority
    // Authorize first then execute the method

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudents() {
        return studentJPARepo.findAll();
    }

    @GetMapping("{studentId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public Student getStudent(@PathVariable int studentId) {
        return studentJPARepo.findById(studentId).get();

    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity registerNewStudent(@RequestBody Student theStudent) {
        studentJPARepo.save(theStudent);
        return ResponseEntity.ok("Student registered");
    }

    @DeleteMapping("/delete/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity deleteStudent(@PathVariable int studentId) {
        studentJPARepo.deleteById(studentId);
        return ResponseEntity.ok("Student deleted");
    }

    @PutMapping("/update/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity updateStudent(@PathVariable int studentId, @RequestBody Student thStudent) {
        thStudent.setStudentId(studentId);
        studentJPARepo.save(thStudent);
        return ResponseEntity.ok("Student updated");
    }

}
