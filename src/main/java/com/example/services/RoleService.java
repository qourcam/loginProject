package com.example.services;

import com.example.entities.Role;
import com.example.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gorkem on 6.04.2017.
 */

@Service("roleService")
public class RoleService {

    @Autowired
    public RoleRepository roleRepository;


    public RoleService (RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }

    public Role findRole(int id){
        return roleRepository.findOne(id);
    }

    public List<Role> getAll(){
        List<Role> list= new ArrayList<>();
        for(Role r : roleRepository.findAll())
        {
            list.add(r);
        }
        return list;
    }

    public Role findRoleById(int id) {return roleRepository.findRoleById(id); }
}
