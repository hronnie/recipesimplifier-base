package com.codeproj.recipesimplifierbase.data.dao;

import com.codeproj.recipesimplifierbase.model.User;

public interface UserDAO {
	
	public User findUserByEmail(String email);
	public void saveUser(User user);
	
}
