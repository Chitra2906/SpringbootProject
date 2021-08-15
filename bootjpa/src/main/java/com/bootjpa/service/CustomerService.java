package com.bootjpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootjpa.dao.UserRepo;
import com.bootjpa.model.User;
import com.fasterxml.jackson.databind.util.JSONPObject;

@Service
public class CustomerService {

	private final Logger logger =LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserRepo repo;
	
	public String addCustomer(User user) {
		logger.info("Entered addCustomer service method......");
		logger.info("Saving the user details...");
		repo.save(user);
		logger.debug( user + " was added in the table User");
		logger.info("User details were added successfully...");
		logger.info("Exiting method .....");
		return user + " was added in the Table User";
	}
	
	public   JSONPObject showCustomer(int userid) {
		logger.info("Entered showCustomer service method.....");
		logger.info("Fetching User details...");
		User user=repo.findById(userid).orElseThrow(null);
		logger.debug("showUser function returned " + user + "  as JSON Object");
		logger.info("User data was fetched successfully");
		logger.info("Exiting method......");
		return new JSONPObject("userobj", user);
}
	
	public String deleteCustomer(int userid) {
		logger.info("Entered deleteCustomer service method....");
		logger.info("Deleting user from the Table user...");
		repo.deleteById(userid);
		logger.info("User with userid " + userid + " was deleted from Table User successfully");
		logger.info("Exiting method...");
		return ("User with userid " + userid + " was deleted from Table User");
		}
	
	

}
