package com.fdmgroup.spgroupcasestudy.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.spgroupcasestudy.model.User;
import com.fdmgroup.spgroupcasestudy.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	UserService userService;
	
	/**
	 * This function should receive a user object and log them in.
	 * 
	 * @param user User to be logged in
	 * @return 200 OK with the userId if log in is successful, 400 Bad Request if login is unsuccessful
	 */
	@PostMapping("/login")
	public ResponseEntity<Object> loginUser(@RequestBody User user) {
		Optional<User> dbUser = userService.validateUserCredentials(user);
		if (dbUser.isPresent()) {
			
			// 200 OK
			return ResponseEntity.ok(dbUser.get().getId());
		}
		
		// 400 Bad Request
		return ResponseEntity.badRequest().body("Login unsuccessful, username or password was incorrect.");
	}
}
