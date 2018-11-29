package ru.lanit.healthapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.lanit.healthapp.model.User;
import ru.lanit.healthapp.repositories.UserDAO;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	
	@Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
	
	@Override
	public User save(User user) {
		return userDAO.save(user);
	}
	
	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	public User doesUserExist(String email) {
		List<User> users = (List<User>) userDAO.findByEmail(email);
		return users.get(0);
	}

	@Override
	public User isValidUser(String email, String password) {
		List<User> users = (List<User>) userDAO.findByEmailAndPassword(email, password);
		return users.get(0);
	}

	@Override
	public User getByEmail(String email) {
		return this.doesUserExist(email);
	}

}
