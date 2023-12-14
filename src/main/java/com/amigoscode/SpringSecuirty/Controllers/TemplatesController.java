package com.amigoscode.SpringSecuirty.Controllers;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;

import com.amigoscode.SpringSecuirty.Entity.Student;
import com.amigoscode.SpringSecuirty.JPARepository.studentJPARepo;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//!Law hatesht8al Full MVC kamel
@Controller // ! in this case we use @Controller instead of @RestController

public class TemplatesController {
    private studentJPARepo studentJPARepo;

    public TemplatesController(studentJPARepo studentJPARepo) {
        this.studentJPARepo = studentJPARepo;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login"; // same name as the file in templates
    }

    @GetMapping("/allstudents")
    public String getCourses(Model theModel) {
        List<Student> Students = studentJPARepo.findAll();
        theModel.addAttribute("students", Students);
        return "students";
    }

}
