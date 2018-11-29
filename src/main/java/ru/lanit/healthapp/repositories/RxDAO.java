package ru.lanit.healthapp.repositories;

import java.util.List;

import ru.lanit.healthapp.model.Rx;

public interface RxDAO {
	List<Rx> findByDoctorId(int doctorId);
	
	List<Rx> findByUserId(int userId);
	
	Rx save(Rx rx);
}
