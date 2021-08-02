package com.example.bootjpa.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.bootjpa.model.User;

//CrudRepository<User is ClassName, Integer is the type of primary key>
public interface UserRepo extends CrudRepository<User, Integer> {

	
}
