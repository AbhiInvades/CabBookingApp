package app.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.Gender;
import app.model.User;
import app.repository.UserRepository;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepository;
	

	public User add_User(String name, String gender, int age) {
		User user = null;
		Gender genderEnum = null;
		try {
			genderEnum = Gender.valueOf(gender);
		}catch(IllegalArgumentException e) {
			System.out.println("Incorrect Gender");
		}catch(Exception e) {
			System.out.println("Error "+e.getMessage());
		}
	    if(name == null) {
	    	System.out.println("Please give username");
	    }
	    else {
	    	user = new User(name, genderEnum, age);
	    	boolean result = userRepository.addUser(user);
	    	if(result) {
	    		System.out.println("Added "+name);
	    	}else {
	    		System.out.println("Username already taken");
	    	}
	    }
		return user;
	}
	
	
}
