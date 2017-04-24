package com.example.repositories;

import com.example.entities.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by gorkem on 28.03.2017.
 */


public interface RoleRepository extends CrudRepository<Role, Integer> {
    public Role findRoleById(int id);
}
