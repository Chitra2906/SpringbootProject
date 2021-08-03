package com.bootjpa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bootjpa.dao.UserRepo;
import com.bootjpa.model.User;
import com.fasterxml.jackson.databind.util.JSONPObject;

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
	
//	@RequestMapping("/showUser")
//	public ModelAndView showUser(@RequestParam int userid) {
//		ModelAndView mv = new ModelAndView("showuser.jsp");
//		User user=repo.findById(userid).orElse(new User());
//		System.out.println(user);
//		mv.addObject(user); //model object
//		System.out.println("user added");
//		return mv;
//	}
	
	
	
	@RequestMapping("/showUser")
	@ResponseBody
	public   JSONPObject showUser(@RequestParam int userid) {
		User user=repo.findById(userid).orElse(new User());
		System.out.println("show user called");
		return new JSONPObject("userobj", user);
	}
	
	
	
	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam int userid) {
		repo.deleteById(userid);
		System.out.println("user deleted");
		return "home.jsp";
		
	}
	
}
