package app.repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import app.model.Gender;
import app.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Repository
@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserRepository {
	
	private HashMap<String, User> users;

	public UserRepository() {
		super();
		// TODO Auto-generated constructor stub
		users = new HashMap<>();
	}

	public User getUser(String userName) {
		if(users.containsKey(userName)) {
			return users.get(userName);
		}else {
			return null;
		}
	}
	
	public boolean addUser(User user) {
		if(users.containsKey(user.getName())) {
			return false;
		}else {
			users.put(user.getName(), user);
			return true;
		}
	}
	
	public boolean updateUser(User user) {
		if(users.containsKey(user.getName())) {
			users.put(user.getName(), user);
			return true;
		}else {
			return false;
		}
	}

	public boolean deleteUser(String userName) {
		if(users.containsKey(userName)) {
			users.remove(users.get(userName));
			return true;
		}else {
			return false;
		}
	}
}
