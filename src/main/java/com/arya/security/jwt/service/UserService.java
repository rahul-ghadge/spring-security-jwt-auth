package com.arya.security.jwt.service;

import java.util.List;

import com.arya.security.jwt.model.User;
import com.arya.security.jwt.model.UserModel;

public interface UserService {

    User save(UserModel user);
    
    List<User> findAll();
    
    User findOne(String username);

    User findById(int id);

    void delete(int id);

    UserModel update(UserModel userModel);
}
