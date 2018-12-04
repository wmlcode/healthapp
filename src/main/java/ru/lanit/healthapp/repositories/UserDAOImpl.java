package ru.lanit.healthapp.repositories;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.lanit.healthapp.model.User;

@Repository
@Transactional(propagation=Propagation.REQUIRED)
public class UserDAOImpl implements UserDAO {

	private SessionFactory sessionFactory;
    
	@Autowired 
	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
    @SuppressWarnings("unchecked")
    @Override
	public List<User> findByEmail(String email) {
    	Session session = this.sessionFactory.getCurrentSession();
        TypedQuery<User> query = session.getNamedQuery("findByEmail");
        query.setParameter("email", email);
        return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByEmailAndPassword(String email, String password) {
		Session session = this.sessionFactory.getCurrentSession();
		TypedQuery<User> query = session.getNamedQuery("findByEmailAndPassword");  
        query.setParameter("email", email); 
        query.setParameter("password", password);
        return query.getResultList();
	}

	@Override
	public User save(User user) {
		Session session = this.sessionFactory.openSession();
		session.save(user);
		session.close();
		return user;
	}
	
	@Override
	public void update(User user) {
		Session session = this.sessionFactory.openSession();
		User persistentUser = session.load(User.class, new Integer(user.getId()));
		Transaction tx = session.beginTransaction();
		persistentUser.setEmail(user.getEmail());
		persistentUser.setPassword(user.getPassword());
		persistentUser.setAge(user.getAge());
		persistentUser.setLastName(user.getLastName());
		session.update(persistentUser);
		tx.commit();
	}

	
}
