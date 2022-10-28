package app.driver;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Driver;
import app.service.IDriverService;
import app.service.IUserService;
import app.service.RideService;

@Service
public class TestCases {

	@Autowired
	private IUserService userService;

	@Autowired
	private IDriverService driverService;
	
	@Autowired
	private RideService rideService; 
	
	public void run() {

		try {
			fill_Users();
			//case1();
			case_simulate_ride();
			
			//case_Using_In_Loop();
			//case_Using_In_Loop();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fill_Users() {
		try {
		userService.add_User("Abhishek", "Male", 19);
		userService.add_User("Abhijit", "Male", 19);
		userService.add_User("Ashish", "Male", 19);
		int[] location = new int[2];
	
		location[0]=0;
		location[1]=1;
		//both drivers at 0,1
		driverService.add_Driver("Menaka", "Female", 19, "Swift K2 2334",location, 1);
		driverService.add_Driver("Abhijit", "Male", 19, "Wagonr F3 2445", location);
		int[] location1 = new int[2];
		location1[0]=1;
		location1[1]=1;
		driverService.add_Driver("Manoj", "Male", 40, "Maruti Suzuki MH 2335", location1);
		int[] location2 = new int[2];
		location2[0]=5;
		location2[1]=5;
		driverService.add_Driver("Sunita", "Female", 40, "Maruti Suzuki MH 2335", location2);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void case_Using_In_Loop() {
		int[] source = new int[2];
		int[] destination = new int[2];
		
		source[0]=3;
		source[1]=3;
		rideService.choose_Ride_in_loop("Abhishek", source, destination, 5);
		
		source[0]=8;
		source[1]=8;
		rideService.choose_Ride_in_loop("Abhishek", source, destination, 5);
	}
	
	public void case1() throws Exception {
		int[] destination = new int[2];
		int[] source= new int[2];
		source[0]=1;
		source[1]=1;
		List<Driver> list = rideService.find_Ride("Abhishek", source, destination);
		if(list != null && list.size() > 0) {
			System.out.println("\nFollowing Drivers are found.");
			for(Driver d : list) {
				System.out.println(d);
			}
			rideService.choose_Ride("Abhishek", list.get(0).getName());
		}
		
		source[0]=7;
		source[1]=7;
		list = rideService.find_Ride("Ashish", source, destination);
		if(list != null && list.size() > 0) {
			System.out.println("\nFollowing Drivers are found.");
			for(Driver d : list) {
				System.out.println(d);
			}
			rideService.choose_Ride("Ashish", list.get(0).getName());
		}
		source[0]=8;
		source[1]=8;
		list = rideService.find_Ride("Abhjit", source, destination);
		if(list != null && list.size() > 0) {
			System.out.println("\nFollowing Drivers are found.");
			for(Driver d : list) {
				System.out.println(d);
			}
			rideService.choose_Ride("Abhijit", list.get(0).getName());
		}
	}
	public void case_simulate_ride() throws Exception{
		int[] destination = new int[2];
		int[] source= new int[2];
		List<Driver> list;
		source[0]=0;
		source[1]=1;
		
			rideService.choose_Ride_in_loop("Abhishek", source, destination, 5);
		
		Thread.sleep(300);
		
			rideService.choose_Ride_in_loop("Abhishek", source, destination, 5);
		
		Thread.sleep(3000);
		System.out.println("---------------");
		list = rideService.find_Ride("Abhishek", source, destination);
		if(list != null && list.size() > 0) {
			System.out.println("\nFollowing Drivers are found.");
			for(Driver d : list) {
				System.out.println(d);
			}
			rideService.choose_Ride("Abhishek", list.get(0).getName());
		}
		
		list = rideService.find_Ride("Abhishek", source, destination);
		if(list != null && list.size() > 0) {
			System.out.println("\nFollowing Drivers are found.");
			for(Driver d : list) {
				System.out.println(d);
			}
			rideService.choose_Ride("Abhishek", list.get(0).getName());
		}
	}
	
}
