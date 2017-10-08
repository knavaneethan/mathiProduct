package com.mathi.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mathi.entity.User;
import com.mathi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;

    @RequestMapping(method = RequestMethod.GET)    
    @RequiresPermissions("user:access")
    public List<User> getAllUsers() {
    	List<User> list = null;
			list = userService.getAllUsers();
		
		return list;
	} 
}
