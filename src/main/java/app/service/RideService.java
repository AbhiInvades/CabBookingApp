package app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import app.model.Driver;
import app.model.Ride;
import app.model.RideStatus;
import app.model.User;
import app.repository.DriverRepository;
import app.repository.MapRepository;
import app.repository.RideRepository;
import app.repository.UserRepository;

@Service
public class RideService {
	
	private static long rideCount = 0;
	
	@Autowired
	private RideRepository rideRepository;
	
	@Autowired
	private DriverRepository driverRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private MapRepository mapRepo;
	
	//extra
	public String choose_Ride_in_loop(String userName, int[] source, int[] destination, int distance) {
		System.out.println("\nChoosing driver for "+userName+" at "+source[0]+","+source[1]);
		Driver driver = null;
		Ride ride = null;
		try {
			int[] a = find_Ride_helper(source[0], source[1], distance);
			PriorityBlockingQueue<Driver>[][] q = mapRepo.getMap();
			if(a != null) {
				synchronized(q[a[0]][a[1]]) {
					driver = q[a[0]][a[1]].poll();
					ride = new Ride(userName,driver.getName(),source, destination, ++rideCount, RideStatus.Ongoing);
					rideRepository.addRide(ride);
				}
				
				
				//only simulate ride if destination is given.
				if(ride.getDestination() != null) {
					RideRunning rideThread = new RideRunning(ride, rideRepository, mapRepo, driverRepo);
					Thread thread = new Thread(rideThread);
					thread.start();
				}
				System.out.println("Booked ride with "+driver.getName());
			}
			if(driver == null) {
				//ride = new Ride(userName, driver.getName(), source, destination, rideCount++);
				System.out.println("Cant find driver, will try again in 2 second.");
				Thread.sleep(2000);
				
				return choose_Ride_in_loop(userName, source, destination, distance);
			}
			return driver.getName();
		}catch(InterruptedException e) {
			e.printStackTrace();
			return null;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
		
	
	public int[] find_Ride_helper(int i, int j, int distance ) throws Exception {
		int[] r = new int[3];
		r[2]=-1;
		PriorityBlockingQueue<Driver>[][] q = mapRepo.getMap(); 
		if(distance<0 || i>q.length || i<0 || j>q.length || j<0) {
			return null;
		}else {
			if(q[i][j] != null && q[i][j].size() > 0) {
				
				r[0]=i;
				r[1]=j;
				r[2]=distance;
				return r;
			}else {
				int[] t;
				t=find_Ride_helper(i+1,j, distance-1);
				if(t!=null && t[2]>r[2]) r=t;
				t=find_Ride_helper(i,j+1, distance-1);
				if(t!=null && t[2]>r[2]) r=t;
				t=find_Ride_helper(i-1,j, distance-1);
				if(t!=null && t[2]>r[2]) r=t;
				t=find_Ride_helper(i,j-1, distance-1);
				if(t!=null && t[2]>r[2]) r=t;
				if(r[2] != -1) {
					return r;
				}else {
					return null;
				}
			}
		}
	}
	
	public List<Driver> find_Ride(String userName, int[] source, int[] destination){
		System.out.println("\nFinding drivers for "+userName+" at "+source[0]+","+source[1]);
		try {
			int[] a = find_Ride_helper(source[0], source[1], 5);
			
			if(a != null) {
				PriorityBlockingQueue<Driver> q = mapRepo.getMap()[a[0]][a[1]];
				List<Driver> list = new ArrayList<Driver>();
				for(Driver driver : q) {
					list.add(driver);
				}
				
				return list;
			}else {
				System.out.println("Could not find ride");
				return null;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void choose_Ride(String userName, String driverName) throws Exception {
		User user = userRepo.getUser(userName);
		Driver driver = driverRepo.getDriver(driverName);
		if(user != null && driver != null) {
			Ride ride = new Ride(userName, driverName, ++rideCount);
			PriorityBlockingQueue<Driver> q = mapRepo.getMap()[driver.getLocation()[0]][driver.getLocation()[1]];
			
//				synchronized(q[driver.getLocation()[0]][driver.getLocation()[1]]) {
					driver = q.poll();
//				}
				if(driver != null) {
					System.out.println("Ride booked with "+driver.getName());
					rideRepository.addRide(ride);
					
					
				}else {
					System.out.println("Sorry ride booked by someone else.");
				}
		}else {
			System.out.println("Incorrect details, please try again");
		}
	}
	
	
}


class RideRunning extends Ride implements Runnable {

	private RideRepository rideRepository;
	
	private MapRepository mapRepo;
	
	private DriverRepository driverRepo;
	
	public void run() {
		// TODO Auto-generated method stub
		int distance1 = Math.abs(destination[0]-source[0]);
		int distance= Math.abs(destination[1]- source[1])+distance1;
		try {

			Thread.sleep(distance*1000);
			//System.out.println("rideno"+rideNo);
			rideRepository.getRide(rideNo).setStatus(RideStatus.Old);
			Driver driver = driverRepo.getDriver(getDriverName());
			if(driver != null) {
				mapRepo.addDriver(driver);
			}
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public RideRunning(Ride ride, RideRepository rideRepo, MapRepository mapRepo, DriverRepository driverRepo) {
		this.driverName = ride.getDriverName();
		this.source = ride.getSource();
		this.destination = ride.getDestination();
		this.rideNo = ride.getRideNo();
		this.rideRepository=rideRepo;
		this.mapRepo=mapRepo;
		this.driverRepo=driverRepo;
	}
	
}