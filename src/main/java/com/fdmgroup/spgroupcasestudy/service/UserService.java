package com.fdmgroup.spgroupcasestudy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.spgroupcasestudy.model.User;
import com.fdmgroup.spgroupcasestudy.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	/**
	 * This function validates a user's credentials and checks if the application should log them in.
	 * 
	 * @param user The user object created by the login form.
	 * @return an Optional which is empty whenever validation has failed, and which contains the validated user
	 * if validation has succeeded.
	 */
	public Optional<User> validateUserCredentials(User user) {
		Optional<User> dbOptionalUser = userRepo.findUserByUsername(user.getUsername());
		
		if (dbOptionalUser.isEmpty()) {
			
			// No user with such username exists, return the empty optional
			return dbOptionalUser;
		} else {
			
			// User exists and passwords match
			if (dbOptionalUser.get().getPassword().equals(user.getPassword())) {
				
				return dbOptionalUser;
			}			
			
		}
		
		// User exists but passwords don't match
		return Optional.empty();
	}

}
