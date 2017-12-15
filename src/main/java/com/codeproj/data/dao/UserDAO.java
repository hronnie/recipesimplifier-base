package com.codeproj.data.dao;

import com.codeproj.model.User;

public interface UserDAO {
	
	public User findUserByEmail(String email);
	public void saveUser(User user);
	
}
