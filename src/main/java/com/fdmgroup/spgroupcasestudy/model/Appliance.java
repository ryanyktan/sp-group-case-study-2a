package com.fdmgroup.spgroupcasestudy.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appliance {

	@Id
	@GeneratedValue
	private long id;
	
	private String serialNumber;
	private String brand;
	private String model;
	private String status;
	private LocalDate dateBought;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Appliance() {
		super();
	}

	public Appliance(String serialNumber, String brand, String model, String status, LocalDate dateBought) {
		super();
		this.serialNumber = serialNumber;
		this.brand = brand;
		this.model = model;
		this.status = status;
		this.dateBought = dateBought;
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

	public LocalDate getDateBought() {
		return dateBought;
	}

	public void setDateBought(LocalDate dateBought) {
		this.dateBought = dateBought;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}
