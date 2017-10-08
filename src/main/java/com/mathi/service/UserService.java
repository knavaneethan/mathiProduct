package com.mathi.service;

import java.util.List;

import com.mathi.entity.User;

public interface UserService {

	public List<User> getAllUsers() ;

	void save(User user);
}
