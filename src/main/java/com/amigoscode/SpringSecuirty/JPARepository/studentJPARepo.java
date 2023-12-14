package com.amigoscode.SpringSecuirty.JPARepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amigoscode.SpringSecuirty.Entity.Student;

public interface studentJPARepo extends JpaRepository<Student, Integer> {

}
