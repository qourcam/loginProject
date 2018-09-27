package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.services.RoleService;
import com.example.services.UserService;

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
		return userService.findUserByUsername(auth.getName()).getRole().getName();
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView rolDuzenleme() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("users", userService.getAll());
		modelAndView.addObject("roles", roleService.getAll());
		modelAndView.setViewName("editRole");
		return modelAndView;
	}

}
