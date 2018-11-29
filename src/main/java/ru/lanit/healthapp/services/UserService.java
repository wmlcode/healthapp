package ru.lanit.healthapp.services;

import ru.lanit.healthapp.model.User;

public interface UserService {
	
	User save(User user);
	
	void update(User user);
	
	User doesUserExist(String email);
	
	User getByEmail(String email);
	
	User isValidUser(String email, String password);
}
