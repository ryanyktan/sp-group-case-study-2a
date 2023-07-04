package com.fdmgroup.spgroupcasestudy.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	private long id;
	
	private String username;
	private String password;
	
	@OneToMany(mappedBy = "user")
	private List<Appliance> appliances;
	
	public User() {
		super();
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public long getId() {
		return id;
	}
	
	
}
