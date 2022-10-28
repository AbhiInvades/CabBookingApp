package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Driver;
import app.model.Gender;
import app.model.User;
import app.repository.DriverRepository;
import app.repository.MapRepository;

@Service
public class DriverService implements IDriverService{

	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private MapRepository mapRepository;
	
	public Driver add_Driver(String name, String gender, int age, String carDetails, int[] location) {
		Driver driver = null;
		Gender genderEnum = null;
		try {
			genderEnum = Gender.valueOf(gender);
		
		if(name != null) {
			driver = new Driver(name, genderEnum, age, carDetails, location);
			if(!driverRepository.addDriver(driver)) {
				return driver=null;
			}else {
				//concurrent
				mapRepository.addDriver(driver);
			}
		}else {
			System.out.println("Please provide correct name");
		}
		}catch(IllegalArgumentException e) {
			System.out.println("Incorrect Gender");
		}catch(Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		return driver;
	}

	public Driver add_Driver(String name, String gender, int age, String carDetails, int[] location, int rating) {
		Driver driver = null;
		Gender genderEnum = null;
		try {
			genderEnum = Gender.valueOf(gender);
		
		if(name != null) {
			driver = new Driver(name, genderEnum, age, carDetails, location, rating);
			if(!driverRepository.addDriver(driver)) {
				return driver=null;
			}else {
				//concurrent
				mapRepository.addDriver(driver);
			}
		}else {
			System.out.println("Please provide correct name");
		}
		}catch(IllegalArgumentException e) {
			System.out.println("Incorrect Gender");
		}catch(Exception e) {
			System.out.println("Error "+e.getMessage());
		}
		return driver;
	}
	
	public boolean rate_Driver(String name, int rating) {
		Driver driver = driverRepository.getDriver(name);
		if(driver != null ) {
			driver.setRating(rating);
			driverRepository.updateDriver(driver);
			return true;
		}
		return false;
	}
	
}
