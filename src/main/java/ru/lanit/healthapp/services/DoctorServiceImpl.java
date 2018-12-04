package ru.lanit.healthapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.lanit.healthapp.model.Doctor;
import ru.lanit.healthapp.model.User;
import ru.lanit.healthapp.repositories.DoctorDAO;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class DoctorServiceImpl implements DoctorService {
	
	private DoctorDAO doctorDAO;
	private UserService userService;
	
	@Autowired
	public DoctorServiceImpl(DoctorDAO doctorDAO, UserService userService) {
	    this.doctorDAO = doctorDAO;
	    this.userService = userService;
	}
	
	@Override
	public List<Doctor> findBySpeciality(String specialityCode) {
		return doctorDAO.findBySpecialityCode(specialityCode);
	}

	@Override
	public List<Doctor> findAll() {
		return doctorDAO.findAll();
	}

	@Override
	public int findCount() {
		return doctorDAO.findAllCount();
	}

	@Override
	public Doctor findByUserEmailAddress(String email) {
		User user;
		try {
			user = userService.getByEmail(email);
		} catch (Exception e) {
			return null;
		}
		return this.findByUserId(user.getId());
	}

	@Override
	public Doctor findByUserId(int userId) {
		return doctorDAO.findByUserId(userId);
	}
	
	@Override
	public void save(Doctor doctor) {
	}

	@Override
	public void addDoctor(User user, String specialityCode) {
		if(user.getRole() == 1) {
			Doctor doctor = new Doctor();
			doctor.setUser(user);
			doctor.setSpecialityCode(specialityCode);
			doctorDAO.save(doctor);
		}
	}

}
