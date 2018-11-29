package ru.lanit.healthapp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RxDTO {
	private String patientId;
	private String patientName;
	private String symptoms;
	private String medicine;
}
