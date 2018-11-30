package ru.lanit.healthapp.services;

import java.util.List;

import ru.lanit.healthapp.model.Doctor;
import ru.lanit.healthapp.model.User;


public interface DoctorService {
	
	void save(Doctor doctor);
	
	List<Doctor> findBySpeciality(String specialityCode);
	
	List<Doctor> findAll();
	
	Doctor findByUserEmailAddress(String email);
	
	int findCount();
	
	Doctor findByUserId(int userId);

	void addDoctor(User user, String specialityCode);
}
