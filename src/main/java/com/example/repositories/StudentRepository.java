package com.example.repositories;

import com.example.entities.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by gorkem on 13.04.2017.
 */


public interface StudentRepository extends CrudRepository<Student, Integer> {
    public Student findStudentByName(String name);
}
