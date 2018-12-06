package ru.lanit.healthapp.repositories;


import java.util.List;

import ru.lanit.healthapp.model.Doctor;

public interface DoctorDAO {
	
	List<Doctor> findAll();
	
	List<Doctor> findBySpecialityCode(String code);
	
	int findAllCount();

	Doctor findByUserId(int userId);

	Doctor save(Doctor doctor);
}
