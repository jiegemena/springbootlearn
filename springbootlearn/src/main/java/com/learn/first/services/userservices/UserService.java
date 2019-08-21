package com.learn.first.services.userservices;

import com.learn.first.entitys.User;
import com.learn.first.repositorys.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    @Autowired
    UserRepository _userRepository;
    
    public User getUserByName(String name){
        return _userRepository.findByUserName(name);
    }

    public void Add(User user){
        _userRepository.save(user);
    }
}