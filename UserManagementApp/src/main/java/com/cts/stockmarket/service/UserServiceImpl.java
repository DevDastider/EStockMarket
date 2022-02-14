package com.cts.stockmarket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.cts.stockmarket.exceptions.UserIdAlreadyExistsException;
import com.cts.stockmarket.model.User;
import com.cts.stockmarket.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		
		List<User> userList= userRepository.findAll();
		
		if(userList!=null & userList.size()>0) {
			return userList;
		}
		else
			return null;
	}

	@Override
	public User addUser(User user) throws UserIdAlreadyExistsException {
		
		if(user!=null) {
			Optional<User> optional= userRepository.findById(user.getId());
			if(optional.isPresent())
				throw new UserIdAlreadyExistsException();
			
			return userRepository.saveAndFlush(user);
		}
			
		else
			return null;
	}

	@Override
	public boolean validateUser(String username, String password) {
		User user= userRepository.validateUser(username, password);
		//System.out.println("User: "+ user.getUsername());
		
		if(user!=null) {
			return true;
		}
		else
			return false;
	}
	
	//Listening for updates
	@KafkaListener(topics="companysheet", groupId="mygroup")
	public void consumeFromCompanyTopic(String message) {
		System.out.println("New Update:");
		System.out.println(message);
	}

}
