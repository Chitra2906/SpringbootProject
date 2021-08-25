package com.bootjpa.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.bootjpa.model.User;
import com.bootjpa.service.CustomerService;
import com.fasterxml.jackson.databind.util.JSONPObject;


@RestController
public class UserController {

	private final Logger logger =LoggerFactory.getLogger(this.getClass());


	@Autowired
	private CustomerService customerService;

//	@RequestMapping("/")
	@GetMapping("/")
	public String home() {
		logger.info("Page loaded successfully");
		return "Hello User";

	}

	// How to test -->http://localhost:8080/addUser
//	@RequestMapping("/addUser")
	@PostMapping("/addUser")
	public String addUser(@RequestBody User user) {
		logger.info("Making Service call to add User");
		return customerService.addCustomer(user);

	}

	// How to test ? --->	http://localhost:8080/showUser?userid=104
//	@RequestMapping("/showUser")
//	@GetMapping("/showUser")
//	@ResponseBody
//	public   JSONPObject showUser(@RequestParam int userid) {
//		logger.info("Making service call to show User detail");
//		return customerService.showCustomer(userid);
//	}
	
	// How to test ? --->	http://localhost:8080/showUser/104
	@GetMapping("/showUser/{userid}")
	@ResponseBody
	public   JSONPObject showUser(@PathVariable int userid) {
		logger.info("Making service call to show User detail");
		return customerService.showCustomer(userid);
	}



	//How to test ? --> http://localhost:8080/deleteUser/101
//	@RequestMapping("/deleteUser")
	@DeleteMapping("/deleteUser/{userid}")
	public String deleteUser(@PathVariable int userid) {
		logger.info("Making service call to delete user from the table...");
		return customerService.deleteCustomer(userid);
	}
	
//	@ExceptionHandler({NullPointerException.class , EmptyResultDataAccessException.class})
//	public String NoSuchUserExistsExceptionHandler(Exception e) {
//		logger.error("Error occurred :  " + e);
//		return "Error occurred : No such user exists";
//		
//	}
//	
//	@ExceptionHandler(value = Exception.class)
//	public String exceptionHandler(Exception e) {
//		logger.error("Error occurred :  " + e);
//		return "Error occurred";
//		
//	}


}
