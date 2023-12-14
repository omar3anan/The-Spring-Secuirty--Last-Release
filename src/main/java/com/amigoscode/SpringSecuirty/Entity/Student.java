package com.amigoscode.SpringSecuirty.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students") // to go to the database and create a table called students
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer studentId;
    @Column(name = "student_name")
    private String studentName;

    // ! MOHEMMMMMM WENTA SHA8AL JPA AWYYYYYYYYYYYYY
    public Student() {
        this.studentId = null;
        this.studentName = null;

    }

    public Student(Integer studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public Integer getStudentId() {
        return this.studentId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    // setters
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

}
