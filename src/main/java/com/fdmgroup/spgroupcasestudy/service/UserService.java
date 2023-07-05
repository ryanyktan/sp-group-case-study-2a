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
	 * @return true if there exists an identical user in the database, false if there does not.
	 */
	public boolean validateUserCredentials(User user) {
		Optional<User> dbOptionalUser = userRepo.findUserByUsername(user.getUsername());
		
		if (dbOptionalUser.isEmpty()) {
			
			// No user with such username exists
			return false;
		} else {
			
			// User exists and passwords match
			if (dbOptionalUser.get().getPassword().equals(user.getPassword())) {
				
				return true;
			}			
			
		}
		
		// User exists but passwords don't match
		return false;
	}

}
