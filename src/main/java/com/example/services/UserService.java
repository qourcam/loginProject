package com.example.services;

import com.example.entities.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gorkem on 31.03.2017.
 */

@Service("userService")
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User findUser(int id){
        return userRepository.findOne(id);
    }

    public List<User> getAll(){
        List<User> list= (List<User>) userRepository.findAll();
        return list;
    }

    public String getUserName(int id){
        return userRepository.findOne(id).getUsername();
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(int id) { userRepository.delete(id);}

    public User findUserByUsername (String username){
        return userRepository.findByUsername(username);
    }

    // Id'si büyük olandan başlayarak küçüğe doğru ilk 10 kişiyi getiriyor.
    public List<User> findTop10ByIdDesc(){ return userRepository.findTop10ByOrderByIdDesc(); }
}
