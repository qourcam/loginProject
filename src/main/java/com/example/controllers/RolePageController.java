package com.example.controllers;

import com.example.entities.Role;
import com.example.services.RoleService;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by gorkem on 11.04.2017.
 */

@Controller
@RequestMapping(value = "/role")
public class RolePageController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    private String getRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUserByUsername(auth.getName()).getRole().getRole();
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView rolDuzenleme(){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.addObject("users", userService.getAll());
        modelAndView.addObject("roles", roleService.getAll());
        modelAndView.addObject("niyazi",new Role());
        modelAndView.setViewName("editRole");
        System.out.println(userService.findUser(2).getRole().getRole());
        return modelAndView;
    }

}
