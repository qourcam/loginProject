package com.example.services;

import com.example.entities.Student;
import com.example.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
        return  studentRepository.findAll();
    }

    public List<Student> findAll(Specification spec){
        return studentRepository.findAll(spec);
    }

    public Student findByName(String name){
        return studentRepository.findStudentByName(name);
    }

    public List<Student> findByLastname(String lastname, Pageable pageable){
        Page<Student> page=studentRepository.findByLastname(lastname, pageable);
        List<Student> list= page.getContent();
        return list; }

    public List<Student> findByLastnameOrName(String lastname, String name){

        return studentRepository.findByLastnameOrName(lastname, name); }
}
