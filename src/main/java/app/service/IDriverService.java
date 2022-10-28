package app.service;

import app.model.Driver;

public interface IDriverService {
	
	public Driver add_Driver(String name, String gender, int age, String carDetails, int[] location); 

	public boolean rate_Driver(String name, int rating);

	public Driver add_Driver(String name, String gender, int age, String carDetails, int[] location, int rating);
	
}
