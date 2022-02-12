package com.cts.stockmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.stockmarket.model.User;
import com.cts.stockmarket.service.IUserService;

@RestController
@RequestMapping("api/v1.0")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers() {
		
		List<User> userList= userService.getAllUsers();
		
		if(userList!=null)
			return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
		
		return new ResponseEntity<String>("List is empty", HttpStatus.NO_CONTENT);
	}
}
