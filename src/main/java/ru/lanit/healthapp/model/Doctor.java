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
@Table(name="doctor")
@NamedQueries({
    @NamedQuery(
        name = "findBySpeciality",
        query = "FROM Doctor u WHERE u.specialityCode = :specialityCode"
    ),
    @NamedQuery(
        name = "findAll",
        query = "FROM Doctor"
    ),
    @NamedQuery(
        name = "findAllCount",
        query = "SELECT count(d) FROM Doctor d"
    ),
    @NamedQuery(
        name = "findById",
        query = "FROM Doctor d WHERE d.user.id = :id"
    ),
})
public class Doctor implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "speciality_code") 
	private String specialityCode;
	@Column(name = "create_time") 
	private Timestamp createTime;
	@Column(name = "last_updated") 
	private Timestamp lastUpdated;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

}
