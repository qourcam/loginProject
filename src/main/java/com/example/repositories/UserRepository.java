package com.example.repositories;

import com.example.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by gorkem on 28.03.2017.
 */

public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByUsername(String username);
}
