package app.repository;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import app.model.Driver;

class DriverRatingComparator implements Comparator<Driver> {

	@Override
	public int compare(Driver o1, Driver o2) {
		// TODO Auto-generated method stub
		return o2.getRating()-o1.getRating();
	}
	
}

@Repository
public class MapRepository {
	
	//maintains map of available drivers. drivers will be removed from map as soon as they get booked.
	private PriorityBlockingQueue<Driver>[][] map;
	private ArrayList<Integer>[][] a;
	private int size;

	public MapRepository() {
		super();
		map = new PriorityBlockingQueue[20][20];
		this.size=20;
	}

	public MapRepository(int size) {
		super();
		this.size = size;
		map = new PriorityBlockingQueue[size][size];
	}
	
	public void addDriver(Driver driver) {
		int[] location = driver.getLocation();
		if(map[location[0]][location[1]] == null) {
			map[location[0]][location[1]] = new PriorityBlockingQueue<Driver>(5, new DriverRatingComparator());
		}
		map[location[0]][location[1]].add(driver);
	}
	
	public boolean removeDriver(Driver driver) {
		int[] location = driver.getLocation();
		if(map[location[0]][location[1]] != null) {
			if(map[location[0]][location[1]].contains(driver) ) {
				map[location[0]][location[1]].remove(driver);
				return true;
			}
		}
		return false;
	}
	
	public PriorityBlockingQueue<Driver>[][] getMap(){
		return map;
	}

	@Override
	public String toString() {
		return "MapRepository [map=" + Arrays.toString(map) + "]";
	}
	
	
	
}
