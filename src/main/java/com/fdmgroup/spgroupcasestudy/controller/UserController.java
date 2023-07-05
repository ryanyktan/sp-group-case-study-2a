package com.fdmgroup.spgroupcasestudy.controller;

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
	 * @param user
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody User user) {
		if (userService.validateUserCredentials(user)) {
			// 200 OK
			return ResponseEntity.ok(user);
		}
		
		// 400 Bad Request
		return ResponseEntity.badRequest().build();
	}
}
