package com.mathi.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.mathi.MyApplication;
import com.mathi.configuration.AppConfig;
import com.mathi.entity.User;
import com.mathi.service.UserService;

public class AppUser {

	public static void main(String args[]) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		UserService service = (UserService) context.getBean("userService");

		User user = new User();
		user.setEmail("test4@nasf.com");
		user.setPassword("asf");
		
		service.save(user);
		
		context.close();
	}
}
