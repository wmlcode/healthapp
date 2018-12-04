package ru.lanit.healthapp.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="rx")
@NamedQueries({
    @NamedQuery(
        name = "findByDoctorId",
        query = "FROM Rx r WHERE r.doctor.user.id = :id"
    ),
    @NamedQuery(
		name = "findByUserId",
		query = "FROM Rx r WHERE r.user.id = :id"
	),
})
public class Rx {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;	
	private String symptoms;
	private String medicine;	
	@Column(name = "create_time") private Timestamp createTime;
	@Column(name = "last_updated") private Timestamp lastUpdated;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

}
