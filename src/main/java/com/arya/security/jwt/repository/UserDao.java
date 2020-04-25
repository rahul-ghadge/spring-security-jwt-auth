package com.arya.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arya.security.jwt.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String userName);
    
}
