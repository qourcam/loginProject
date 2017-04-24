package com.example.controllers;

import com.example.entities.Student;
import com.example.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by gorkem on 13.04.2017.
 */
@RestController
@RequestMapping(value = "/student")
public class StudentRestController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value ="/find={name}", method = RequestMethod.GET)
    public Student findByName(@PathVariable  String name){
        return studentService.findByName(name);
    }

    @RequestMapping(value = "/deneme")
    public List<Student> deneme(){
        List<Student> list= studentService.getAll();
        return list;
    }
}
