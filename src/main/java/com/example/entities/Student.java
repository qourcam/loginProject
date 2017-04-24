package com.example.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by gorkem on 13.04.2017.
 */

@Entity
@Table(name = "student")
public class Student implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String lastname;
    private int studentNumber;
    private String major;

    @ManyToOne
    private School school;


    public Student(){}

    public Student(String name, String lastname, int studentNumber, String major, School school) {
        this.name = name;
        this.lastname = lastname;
        this.studentNumber = studentNumber;
        this.major = major;
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }


}
