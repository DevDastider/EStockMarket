package com.cts.stockmarket.service;

import java.util.List;

import com.cts.stockmarket.exceptions.UserIdAlreadyExistsException;
import com.cts.stockmarket.model.User;

public interface IUserService {
	
	public List<User> getAllUsers();										//To fetch all registered users
	
	public User addUser(User user) throws UserIdAlreadyExistsException;		//For adding user details
	
	public boolean validateUser(String username, String password);			//For validating user credentials while logging in
	
}
