package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.entities.Role;

/**
 * Created by gorkem on 28.03.2017.
 */

public interface RoleRepository extends CrudRepository<Role, Integer> {
	public Role findRoleById(int id);

	public Role findRoleByName(String name);
}
