package com.mathi.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mathi.entity.User;
import com.mathi.util.SessionUtil;

@RestController
@RequestMapping("/logging")
public class LoginController {
	
	@Resource
	private SessionUtil sessionUtil;
    
    @PostMapping
    @RequestMapping("/login")
	Boolean login(@RequestBody User user) {
			return sessionUtil.login(user);
    }
    @PostMapping
    @RequestMapping("/logout")
	Boolean logout() {
			return sessionUtil.logout();
    }
}
