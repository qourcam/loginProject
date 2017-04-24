package com.example.services;

import com.example.entities.Student;
import com.example.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gorkem on 13.04.2017.
 */

@Service("studentService")
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public StudentService (StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    public Student findStudent(int id) {
        return studentRepository.findOne(id);
    }

    public List<Student> getAll(){
        List<Student> list= (List<Student>) studentRepository.findAll();
        return  list;
    }

    public Student findByName(String name){
        return studentRepository.findStudentByName(name);
    }
}
