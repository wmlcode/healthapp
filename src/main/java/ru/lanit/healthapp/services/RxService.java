package ru.lanit.healthapp.services;

import java.util.List;

import ru.lanit.healthapp.model.Rx;

public interface RxService {

	void save(Rx rx);
	
	List<Rx> findByDoctorId(int doctorId);
	
	List<Rx> findByPatientId(int userId);
}
