package com.mathi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mathi.dao.UserDao;
import com.mathi.entity.User;

@Service("userService")

@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
    private UserDao userDao;
		
	
	@Override	
	public List<User> getAllUsers() {
		return userDao.getAllUsers();		
	}
	
	@Override
	public void save(User user)  {
		userDao.save(user);
	}
}
