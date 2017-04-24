package com.example.controllers;

import com.example.entities.User;
import com.example.services.RoleService;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gorkem on 11.04.2017.
 */

@RestController
public class UserRestController {

    @Autowired
    UserService userService;

    //url sonuna find=x şeklinde verilen id'ye sahip User'ı döndürür.
    @RequestMapping(value = "/find={id}", method= RequestMethod.GET )
    public User find(@PathVariable int id){
        User user= userService.findUser(id);
        System.out.println("Bulunan user: " + user.getId() +"  " + user.getUsername());
        return user;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@Valid User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Registration Completed Successfully.");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("edit");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid String username){
        ModelAndView modelAndView = new ModelAndView();
            // user bilgileri gönderilecek
            User user=userService.findUserByUsername(username);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("edit");

        return modelAndView;
    }

    // ismi aranılan kişilerin listesini getirir ama aynı isimde kişi olmasına izin verilmez
    // o sebeple şimdi biraz saçma ama ilerisi için örnek
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public List<User> searchByName(@Valid String name){
        ModelAndView modelAndView = new ModelAndView();
        List<User> users= userService.getAll();
        List<User> list= new ArrayList<>();

        for (User  u : users) {
            if(u.getUsername().equals(name)){
                list.add(u);
            }
        }
        return list;
    }

    //sayfadan alınan user nesnesinin burada kayıt işlemi yapılıyor. problem oluşma ihtimaline karşı binding result
    //kullanılıyor. hata oluşursa hata mesajı verebilecek şekilde. gerçi bende vermiyor ama
    @RequestMapping(value = "/kaydet", method = RequestMethod.POST)
    public ModelAndView yeniKayit(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "Username has already been taken"
                                    + " Check your details!");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("addUser");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Registration Completed Successfully.");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("addUser");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/at");
        userService.deleteUser(id);
        return  modelAndView;
    }

    @RequestMapping(value = "/passwordChange", method = RequestMethod.POST)
    public ModelAndView sifreDegistir(@Valid String oldPass, String newPass, String newPassCheck){
        ModelAndView modelAndView= new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User activeUser=userService.findUserByUsername(auth.getName());
        if (activeUser.getPassword().equals(oldPass)){
            if(newPass.equals(newPassCheck)){
                activeUser.setPassword(newPass);
                userService.saveUser(activeUser);
                modelAndView.addObject("success","İşlem Başarılı");
            }else{
                modelAndView.addObject("fail","İşlem Gerçekleştirilemedi. Şifreleri kontrol edin.");
            }
            modelAndView.setViewName("passwordChange");
        }else{
            modelAndView.setViewName("403");

        }
        return modelAndView;
    }
}
