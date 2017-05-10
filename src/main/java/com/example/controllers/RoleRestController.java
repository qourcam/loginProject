package com.example.controllers;

import com.example.entities.Role;
import com.example.entities.User;
import com.example.services.RoleService;
import com.example.services.UserService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 *    Created by gorkem on 31.03.2017.
 */

// rest controller olması bir web servis göndereceğimiz anlamına gelir.
// yani bir sayfa görüntüleme yapılmayacak. nesne bilgileri görüntülenecek.
@RestController
@RequestMapping(value = "/role")
public class RoleRestController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;



    @RequestMapping(value = "/roles", method= RequestMethod.GET )
    public List<Role> getAllRoles(){
        List<Role> list= roleService.getAll();
        return list;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam int id, int roleId){
        User user= userService.findUser(id);
        Role role=roleService.findRole((roleId));
        user.setRole(role);
        userService.saveUser(user);
        ModelAndView modelAndView = new ModelAndView("redirect:/role/edit");

        return modelAndView;
    }

}
