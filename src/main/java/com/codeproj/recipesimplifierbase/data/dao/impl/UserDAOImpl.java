package com.codeproj.recipesimplifierbase.data.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codeproj.recipesimplifierbase.data.dao.UserDAO;
import com.codeproj.recipesimplifierbase.data.repo.UserRepository;
import com.codeproj.recipesimplifierbase.model.User;


@Service("userDAO")
public class UserDAOImpl implements UserDAO {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username);
        return u;
    }

    public User findById(Long id) throws AccessDeniedException {
    	User  u = userRepository.findOne(id);
        return u;
    }
    
    public List<User> findAll() throws AccessDeniedException {
        List<User> result = userRepository.findAll();
        return result;
    }

}