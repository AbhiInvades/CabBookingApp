package app.model;

public class Ride {
	protected String userName;
	
	protected String driverName;
	
	//extra
	protected int[] source;
	//extra
	protected int[] destination;
	//extra
	protected long rideNo; 
	
	//extra
	protected RideStatus status;

	public Ride() {
		super();
		// TODO Auto-generated constructor stub
		source = new int[2];
		destination = new int[2];
		rideNo=-1;
	}
	
	public Ride(String userName, String driverName) {
		this.userName=userName;
		this.driverName=driverName;
	}
	
	public Ride(String userName, String driverName, Long rideNo) {
		this.userName=userName;
		this.driverName=driverName;
		this.rideNo=rideNo;
	}

	//optional
	public Ride(String userName, String driverName, int[] source, int[] destination, long rideNo, RideStatus status) {
		super();
		this.userName = userName;
		this.driverName = driverName;
		this.source = source;
		this.destination = destination;
		this.rideNo = rideNo;
		this.status=status;
	}

	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getDriverName() {
		return driverName;
	}



	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}



	public long getRideNo() {
		return rideNo;
	}

	public void setRideNo(long rideNo) {
		this.rideNo = rideNo;
	}

	public int[] getSource() {
		return source;
	}
	
	

	public RideStatus getStatus() {
		return status;
	}

	public void setStatus(RideStatus status) {
		this.status = status;
	}

	public void setSource(int[] source) {
		this.source = source;
	}

	public int[] getDestination() {
		return destination;
	}

	public void setDestination(int[] destination) {
		this.destination = destination;
	}
	
}
 