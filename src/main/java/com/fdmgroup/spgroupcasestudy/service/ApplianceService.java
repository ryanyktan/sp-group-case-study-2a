package com.fdmgroup.spgroupcasestudy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.spgroupcasestudy.model.Appliance;
import com.fdmgroup.spgroupcasestudy.model.User;
import com.fdmgroup.spgroupcasestudy.repository.ApplianceRepository;
import com.fdmgroup.spgroupcasestudy.repository.UserRepository;

@Service
public class ApplianceService {

	@Autowired
	ApplianceRepository applianceRepository;
	
	@Autowired
	UserRepository userRepository;

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
	 * This service function deletes an appliance from the database if the appliance exists in the database, and has inactive(old/unused/sold) status.
	 * 
	 * @param id
	 * @return false if no such appliance exists, true if appliance exists and appliance is deleted
	 */
	public boolean deleteAppliance(long id) {
		
		Optional<Appliance> optionalAppliance = applianceRepository.findById(id); 
		
		if (optionalAppliance.isEmpty()) {
			
			return false;
			
		} else {
			// Make sure appliance is inactive
			if (! optionalAppliance.get().getStatus().equalsIgnoreCase("active")) {
				
				applianceRepository.delete(optionalAppliance.get());			
				return true;
				
			} else {
				
				return false;
				
			}
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

	/**
	 * This function searches if an appliance already exists in the repository, by its serial number, brand and model.
	 * 
	 * @param appliance
	 * @return false if it doesn't exist; true if it does exist.
	 */
	public boolean checkIfApplianceExists(Appliance appliance) {
		if ( applianceRepository.findBySerialNumber_Brand_andModel(appliance.getSerialNumber(),
				appliance.getBrand(), appliance.getModel()).size() == 0 ) {
			return false;
		} else {
			return true;
		}
	}

	public List<Appliance> getAllAppliancesForThisUser(long userId) {
		return applianceRepository.findApplianceByUserId(userId);
	}

	public Appliance prepareApplianceForProcessing(Appliance appliance, long userId) {
		Optional<User> optionalUser = userRepository.findUserById(userId);
		
		// No checks required since to get here, user has to log in from the front end which guarantees 
		// that the find function will return a nonempty Optional.
		appliance.setUser(optionalUser.get());
		return appliance;
	}
	
}
