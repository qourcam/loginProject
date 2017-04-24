package com.example.controllers;

import com.example.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by gorkem on 14.04.2017.
 */
@Controller
@RequestMapping("/student")
public class StudentPageController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "list")
    public ModelAndView studentTable(){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.addObject("students", studentService.getAll());
        modelAndView.setViewName("list");
        return modelAndView;
    }


}
