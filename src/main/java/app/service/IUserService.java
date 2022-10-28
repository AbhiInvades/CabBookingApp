package app.service;

import org.springframework.beans.factory.annotation.Autowired;

import app.model.User;
import app.repository.UserRepository;

public interface IUserService {
	
	public User add_User(String name, String gender, int age);
	
}
