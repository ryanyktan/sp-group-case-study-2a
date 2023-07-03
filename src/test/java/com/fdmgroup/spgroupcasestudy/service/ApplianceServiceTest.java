package com.fdmgroup.spgroupcasestudy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fdmgroup.spgroupcasestudy.repository.ApplianceRepository;
import com.fdmgroup.spgroupcasestudy.model.Appliance;

@ExtendWith(MockitoExtension.class)
class ApplianceServiceTest {

	@Mock
	ApplianceRepository mockAppRepo;
	
	@Test
	void getAllAppliances_methodCallsFindAllMethodInApplianceRepository() {
		ApplianceService appService = new ApplianceService();
		appService.setApplianceRepository(mockAppRepo);
		appService.getAllAppliances();
		verify(mockAppRepo, times(1)).findAll();
	}

	@Test
	void deleteAppliance_methodReturnsFalseWhenApplianceDoesNotExist() {
		Optional<Appliance> optional = Optional.empty();
		when(mockAppRepo.findById((long) 1)).thenReturn(optional);
		
		ApplianceService appService = new ApplianceService();
		appService.setApplianceRepository(mockAppRepo);
		assertEquals(false, appService.deleteAppliance(1));
	}
	
	@Test
	void deleteAppliance_methodReturnsTrueWhenApplianceExists() {
		Appliance app = new Appliance();
		Optional<Appliance> optional = Optional.of(app);
		when(mockAppRepo.findById((long) 1)).thenReturn(optional);
		
		ApplianceService appService = new ApplianceService();
		appService.setApplianceRepository(mockAppRepo);
		assertEquals(true, appService.deleteAppliance(1));
	}
	
	@Test
	void updateAppliance_methodReturnsFalseWhenApplianceDoesNotExist() {
		Optional<Appliance> optional = Optional.empty();
		when(mockAppRepo.findById((long) 1)).thenReturn(optional);
		
		ApplianceService appService = new ApplianceService();
		appService.setApplianceRepository(mockAppRepo);
		Appliance app = new Appliance();
		app.setId(1);
		assertEquals(false, appService.updateAppliance(app));
	}
	
	@Test
	void updateAppliance_methodReturnsTrueWhenApplianceExists() {
		Appliance app = new Appliance();
		Optional<Appliance> optional = Optional.of(app);
		when(mockAppRepo.findById((long) 1)).thenReturn(optional);
		
		ApplianceService appService = new ApplianceService();
		appService.setApplianceRepository(mockAppRepo);
		app.setId(1);
		assertEquals(true, appService.updateAppliance(app));
	}
}
