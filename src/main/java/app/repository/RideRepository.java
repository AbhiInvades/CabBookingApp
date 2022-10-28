package app.repository;

import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import app.model.Ride;
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
public class RideRepository {
	private HashMap<Long, Ride> rides;

	public RideRepository() {
		super();
		// TODO Auto-generated constructor stub
		rides= new HashMap<Long, Ride>();
	}

	public HashMap<Long, Ride> getRides() {
		return rides;
	}

	public void setRides(HashMap<Long, Ride> rides) {
		this.rides = rides;
	}

	public Ride getRide(long rideNo) {
		if(rides.containsKey(rideNo)) {
			return rides.get(rideNo);
		}else {
			return null;
		}
	}
	
	public boolean addRide(Ride ride) {
		if(rides.containsKey(ride.getRideNo())) {
			return false;
		}else {
			rides.put(ride.getRideNo(), ride);
			return true;
		}
	}
	
	public boolean updateRide(Ride ride) {
		if(rides.containsKey(ride.getRideNo())) {
			rides.put(ride.getRideNo(), ride);
			return true;
		}else {
			return false;
		}
	}

	public boolean deleteRide(long rideNo) {
		if(rides.containsKey(rideNo)) {
			rides.remove(rides.get(rideNo));
			return true;
		}else {
			return false;
		}
	}
	
	
	//extra
	
}
