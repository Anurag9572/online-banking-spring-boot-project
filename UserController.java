package com.spring.online_banking_spring_boot_project.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.online_banking_spring_boot_project.dao.UserDao;
import com.spring.online_banking_spring_boot_project.entity.User;
import com.spring.online_banking_spring_boot_project.validation.PasswordValidation;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@PostMapping(value = "/saveUser")
	public User saveUserController(@RequestBody User user) {
		
		if(PasswordValidation.isValidPassword(user.getPassword())) {
			user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
			return userDao.saveUserDao(user);	
		}else {
			return null;
		}
	}
}
