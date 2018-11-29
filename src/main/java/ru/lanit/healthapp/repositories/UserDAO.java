package ru.lanit.healthapp.repositories;

import java.util.List;

import ru.lanit.healthapp.model.User;

public interface UserDAO  {
	
	User save(User user);
	
	List<User> findByEmail(String email);
	
	List<User> findByEmailAndPassword(String email, String password);
	
	void update(User user);
	
}
