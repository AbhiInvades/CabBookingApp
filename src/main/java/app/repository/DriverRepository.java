package app.repository;

import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import app.model.Driver;
import app.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Repository
@Getter
@Setter
@ToString
@AllArgsConstructor
public class DriverRepository {
	private HashMap<String, Driver> drivers;

	public DriverRepository() {
		super();
		drivers= new HashMap<String, Driver>();
		// TODO Auto-generated constructor stub
	}

	public HashMap<String, Driver> getdrivers() {
		return drivers;
	}

	public void setdrivers(HashMap<String, Driver> drivers) {
		this.drivers = drivers;
	}

	public Driver getDriver(String DriverName) {
		if(drivers.containsKey(DriverName)) {
			return drivers.get(DriverName);
		}else {
			return null;
		}
	}
	
	public boolean addDriver(Driver Driver) {
		if(drivers.containsKey(Driver.getName())) {
			return false;
		}else {
			drivers.put(Driver.getName(), Driver);
			return true;
		}
	}
	
	public boolean updateDriver(Driver Driver) {
		if(drivers.containsKey(Driver.getName())) {
			drivers.put(Driver.getName(), Driver);
			return true;
		}else {
			return false;
		}
	}

	public boolean deleteDriver(String DriverName) {
		if(drivers.containsKey(DriverName)) {
			drivers.remove(DriverName);
			return true;
		}else {
			return false;
		}
	}
}
