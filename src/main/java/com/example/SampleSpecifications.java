package com.example;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.example.entities.Student;

/**
 * Created by gorkem on 8.05.2017.
 */
public class SampleSpecifications {

	public static Specification<Student> kardesler() {
		return new Specification<Student>() {
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("lastname"), "kol");
			}
		};
	}

}
