package com.example.controllers;

import com.example.entities.User;
import com.example.services.RoleService;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by gorkem on 30.03.2017.
 */

// klasik bir mvc controller(yani bu) ve rest controller arasındaki fark http response body oluşma şeklidir.
// mvc de bir html data dönerken restful web servis ise json object döndürür.
@Controller
public class UserPageController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;


    private String getRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUserByUsername(auth.getName()).getRole().getRole();
    }

    @RequestMapping(value = {"/", "/home"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/login")
    public ModelAndView giris() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/hello")
    public ModelAndView merhaba() {
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.addObject("role", getRole());
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping(value = "/403")
    public String accessDenied() {
        System.out.println("hello");
        return "403";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        return modelAndView;
    }

    @RequestMapping(value = "/at", method = RequestMethod.GET)
    public ModelAndView atagit() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userService.getAll());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("role", getRole());
        modelAndView.setViewName("at");
        return modelAndView;
    }

    //kaydet sayfasına buradan gidiyoruz. ayrıca sayfaya bir user nesnesi yolluyoruzki
    //nesne orada doldurulup buraya kayıt için gönderilsin
    @RequestMapping(value = "/kaydet", method = RequestMethod.GET)
    public ModelAndView kayit() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("addUser");
        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchByName() {
        ModelAndView modelAndView = new ModelAndView();
        String name="";
        modelAndView.addObject("name", name);
        modelAndView.setViewName("search");
        return modelAndView;
    }

    @RequestMapping(value = "/passwordChange", method = RequestMethod.GET)
    public ModelAndView sifreDegistir(){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("passwordChange");
        return modelAndView;
    }

}