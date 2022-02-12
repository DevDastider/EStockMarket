package com.cts.stockmarket.service;

import java.util.List;

import com.cts.stockmarket.model.User;

public interface IUserService {
	
	public List<User> getAllUsers();
	public User addUser(User user);
	public boolean validateUser(String username, String password);
	
}
