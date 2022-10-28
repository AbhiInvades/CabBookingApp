package app.model;

import java.util.Objects;

public class Driver {
	
	private String name;
	
	private Gender gender;
	
	private int age;
	
	private String carDetails;
	
	private int[] location;
	
	private int rating;
	

	public Driver() {
		super();
		// TODO Auto-generated constructor stub
		location = new int[2];
	}

	
	
	public Driver(String name, Gender gender, int age, String carDetails, int[] location, int rating) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.carDetails = carDetails;
		this.location = location;
		this.rating = rating;
	}



	public Driver(String name, Gender gender, int age, String carDetails, int[] location) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.carDetails = carDetails;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCarDetails() {
		return carDetails;
	}

	public void setCarDetails(String carDetails) {
		this.carDetails = carDetails;
	}

	public int[] getLocation() {
		return location;
	}

	public void setLocation(int[] location) {
		this.location = location;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		return Objects.equals(name, other.name);
	}



	@Override
	public String toString() {
		return "Driver [name=" + name + "]";
	}
	
}
