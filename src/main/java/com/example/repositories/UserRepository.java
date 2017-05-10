package com.example.repositories;

import com.example.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by gorkem on 28.03.2017.
 */

public interface UserRepository extends CrudRepository<User, Integer> {
     User findByUsername(String username);

     List<User> findTop10ByOrderByIdDesc();
}
