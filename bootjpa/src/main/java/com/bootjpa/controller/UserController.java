package com.bootjpa.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private final Logger logger =LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserRepo repo;

	@RequestMapping("/")
	public String home() {
		logger.info("Page loaded successfully");
		return "home.jsp";
		
	}
	
	@RequestMapping("/addUser")
	public String addUser(User user) {
		repo.save(user);
		logger.info( user + " was added in the Table User");
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
		boolean userexists=repo.existsById(userid);
		if(userexists) {
			User user=repo.findById(userid).orElse(new User());
			logger.info("showUser function returned " + user + "  as JSON Object");
			return new JSONPObject("userobj", user);
		}
		else {
			logger.error("No such user exists with userid " + userid );
			return new JSONPObject("userobj", "No user exists with userid "+userid);
		}
		
	}
	
	
	
	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam int userid) {
		boolean userexists= repo.existsById(userid);
		if(userexists) {
		repo.deleteById(userid);
		logger.info("User with userid " +userid + " was deleted from Table User");
		}
		
		else{
		logger.error("No such user exists with userid " + userid);
		}
		
		return "home.jsp";
		
	}
	
}
