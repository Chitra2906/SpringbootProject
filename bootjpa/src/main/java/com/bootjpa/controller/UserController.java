package com.bootjpa.controller;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bootjpa.dao.UserRepo;
import com.bootjpa.model.User;
import com.fasterxml.jackson.databind.util.JSONPObject;


@RestController
public class UserController {
	
	private final Logger logger =LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserRepo repo;

	@RequestMapping("/")
	public String home() {
		logger.info("Page loaded successfully");
		return "Hello User";
		
	}

// How to test -->http://localhost:8080/addUser?userid=101&name=Priya
	@RequestMapping("/addUser")
	public String addUser(User user) {
		logger.info("Saving the user details...");
		repo.save(user);
		logger.debug( user + " was added in the table User");
		logger.info("User details were added successfully...");
		return user + " was added in the Table User";
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
	
	
// How to test ? --->	http://localhost:8080/showUser?userid=104
	@RequestMapping("/showUser")
	@ResponseBody
	public   JSONPObject showUser(@RequestParam int userid) {
			logger.info("Fetching User details...");
//			User user=repo.findById(userid).orElse(null);
			User user=repo.findById(userid).orElseThrow(null);
			logger.debug("showUser function returned " + user + "  as JSON Object");
			logger.info("User data was fetched successfully");
			return new JSONPObject("userobj", user);
	}
	
	
//How to test ? --> http://localhost:8080/deleteUser?userid=101
	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam int userid) {
		logger.info("Deleting user from the Table user...");
		repo.deleteById(userid);
		logger.info("User with userid " + userid + " was deleted from Table User successfully");
		return ("User with userid " + userid + " was deleted from Table User");
		}
		

		@ExceptionHandler({NullPointerException.class , EmptyResultDataAccessException.class})
		public String NoSuchUserExistsExceptionHandler(Exception e) {
			logger.error("Error occurred :  " + e);
			return "Error occurred : No such user exixts";
			
		}
		
		@ExceptionHandler(value = Exception.class)
		public String exceptionHandler(Exception e) {
			logger.error("Error occurred :  " + e);
			return "Error occurred";
			
		}
		
	
	
}
