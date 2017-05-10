package com.example.repositories;

import com.example.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by gorkem on 13.04.2017.
 */


public interface StudentRepository extends JpaRepository<Student, Integer>,JpaSpecificationExecutor {
    public Student findStudentByName(String name);

      //@Query(value = "SELECT * FROM Student WHERE lastname = ?1", countQuery = "select count(*) from Student where lastname = ?1",nativeQuery = true)
      Page<Student> findByLastname(String lastname, Pageable pageable);

    // iki kriterli sorgu.   ↓  tam alttaki kod ise entity adının açıkta olmaması için
    @Query("select u from #{#entityName} u where u.name = :name or u.lastname = :lastname")
    List<Student> findByLastnameOrName(@Param("lastname") String lastname, @Param("name") String name);

}
