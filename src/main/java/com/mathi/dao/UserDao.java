package com.mathi.dao;

import java.util.List;

import com.mathi.entity.User;

public interface UserDao {


	public List<User> getAllUsers();
	
	public User getUserByEmail(String email);
}
