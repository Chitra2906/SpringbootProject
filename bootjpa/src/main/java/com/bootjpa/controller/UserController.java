package com.bootjpa.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		repo.save(user);
		logger.info( user + " was added in the Table User");
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
	
	
// How to test ? --->	http://localhost:8080/showUser?userid=11
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
	
	
//How to test ? --> http://localhost:8080/deleteUser?userid=101
	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam int userid) {
		boolean userexists= repo.existsById(userid);
		if(userexists) {
		repo.deleteById(userid);
		logger.info("User with userid " +userid + " was deleted from Table User");
		return ("User with userid " +userid + " was deleted from Table User");
		}
		
		else{
		logger.error("No such user exists with userid " + userid);
		return("No such user exists with userid " + userid);
		}
		
		
		
	}
	
}
