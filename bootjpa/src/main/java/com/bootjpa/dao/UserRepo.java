package com.bootjpa.dao;

import org.springframework.data.repository.CrudRepository;

import com.bootjpa.model.User;

//CrudRepository<User is ClassName, Integer is the type of primary key>
public interface UserRepo extends CrudRepository<User, Integer> {

	
}
