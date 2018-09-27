package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.SampleSpecifications;
import com.example.entities.Student;
import com.example.services.StudentService;

/**
 * Created by gorkem on 13.04.2017.
 */
@RestController
@RequestMapping(value = "/student")
public class StudentRestController {

	@Autowired
	StudentService studentService;

	@RequestMapping(value = "/find={name}", method = RequestMethod.GET)
	public Student findByName(@PathVariable String name) {
		return studentService.findByName(name);
	}

	@RequestMapping(value = "/deneme")
	public List<Student> deneme() {
		List<Student> list = studentService.getAll();
		return list;
	}

	// soyadı parametre olarak alınan kişilerden ilk 5 kişilik kısmın listesini isme
	// göre sıralayarak döndürür.
	@RequestMapping(value = "/soyad={lastname}")
	public List<Student> find(@PathVariable String lastname, Pageable pageable) {
		PageRequest pageRequest = new PageRequest(0, 5, new Sort("name"));
		return studentService.findByLastname(lastname, pageRequest);
	}

	// aldığı iki parametreye göre iki kriteride ayrı ayrı sağlayan elemanları
	// getirir.
	@RequestMapping(value = "/last={lastname},first={name}")
	public List<Student> or(@PathVariable String lastname, @PathVariable String name) {
		return studentService.findByLastnameOrName(lastname, name);
	}

	@RequestMapping(value = "/qwe")
	public List<Student> kisiler() {
		SampleSpecifications ab = new SampleSpecifications();
		Specification<Student> c = ab.kardesler();
		return studentService.findAll(c);
	}

}
