package com.mathi.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mathi.entity.User;
import com.mathi.service.UserService;
import com.mathi.util.SessionUtil;

@RestController
@RequestMapping("/logging")
public class LoginController {

	@Resource
	private UserService userService;
	
	@Resource
	private SessionUtil sessionUtil;
	
    private static final String template = "Hello, %s!";

    @RequestMapping(method = RequestMethod.GET)
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
                            return String.format(template, name+" logging");
    }
    
    @PostMapping
    @RequestMapping("/login")
	Boolean login(@RequestBody User user) {
			return sessionUtil.login(user);
    }

}
