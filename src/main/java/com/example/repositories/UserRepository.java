package com.example.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.User;

/**
 * Created by gorkem on 28.03.2017.
 */

public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsername(String username);

	List<User> findTop10ByOrderByIdDesc();
}
