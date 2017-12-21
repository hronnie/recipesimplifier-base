package com.codeproj.recipesimplifierbase.data.dao;

import java.util.List;

import com.codeproj.recipesimplifierbase.model.User;

public interface UserDAO {
	
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
	
}
