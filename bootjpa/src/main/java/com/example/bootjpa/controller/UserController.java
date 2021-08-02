package com.example.bootjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bootjpa.dao.UserRepo;
import com.example.bootjpa.model.User;

@Controller
public class UserController {
	
	@Autowired
	UserRepo repo;

	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@RequestMapping("/addUser")
	public String addUser(User user) {
		repo.save(user);
		return "home.jsp";
	}
}
