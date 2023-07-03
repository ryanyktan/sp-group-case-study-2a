package com.fdmgroup.spgroupcasestudy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.spgroupcasestudy.model.Appliance;
import com.fdmgroup.spgroupcasestudy.repository.ApplianceRepository;

public class ApplianceService {

	@Autowired
	ApplianceRepository applianceRepository;

	//Purely for mockito testing
	public void setApplianceRepository(ApplianceRepository applianceRepository) {
		this.applianceRepository = applianceRepository;
	}

	/**
	 * 
	 * @return a list of all appliances found in the database.
	 */
	public List<Appliance> getAllAppliances() {
		return applianceRepository.findAll();
	}
	
	/**
	 * This service function creates a new Appliance entity with the desired attributes and saves them to the repository.
	 * 
	 * @param serialNumber
	 * @param brand
	 * @param model
	 * @param status
	 * @param dateBought
	 */
	public Appliance createAppliance(Appliance appliance) {
		return applianceRepository.save(appliance);
	}
	
	/**
	 * This service function deletes an appliance from the database if the appliance exists in the database.
	 * 
	 * @param id
	 * @return false if no such appliance exists, true if appliance exists and appliance is deleted
	 */
	public boolean deleteAppliance(long id) {
		
		Optional<Appliance> optionalAppliance = applianceRepository.findById(id); 
		
		if (optionalAppliance.isEmpty()) {
			
			return false;
			
		} else {
			
			applianceRepository.delete(optionalAppliance.get());
			return true;
		}
	}
	
	/**
	 * This service functions updates an appliance with new details.
	 * 
	 * @param appliance The appliance to be updated
	 * @return false if the existing appliance cannot be found, true if the appliance exists and appliance is updated.
	 */
	public boolean updateAppliance(Appliance appliance) {
		
		// Check if the appliance exists in the database
		if (applianceRepository.findById(appliance.getId()).isEmpty()) {
			
			// No Appliance to update
			return false;
			
		} else {
			
			//Update appliance in database
			applianceRepository.save(appliance);
			return true;
		}
	}
}