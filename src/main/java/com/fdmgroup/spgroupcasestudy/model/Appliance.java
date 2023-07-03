package com.fdmgroup.spgroupcasestudy.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Appliance {

	@Id
	private long id;
	
	private String serialNumber;
	private String brand;
	private String model;
	private String status;
	private LocalDateTime dateBought;
	
	public Appliance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getDateBought() {
		return dateBought;
	}

	public void setDateBought(LocalDateTime dateBought) {
		this.dateBought = dateBought;
	}

	public long getId() {
		return id;
	}
	
	
}
