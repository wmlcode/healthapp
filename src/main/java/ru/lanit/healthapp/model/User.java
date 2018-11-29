package ru.lanit.healthapp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="user")
@NamedQueries({
    @NamedQuery(
        name = "findByEmail",
        query = "from User u where u.email = :email"
        ),
    @NamedQuery(
    	name = "findByEmailAndPassword",
    	query = "from User u where u.email= :email and u.password = :password"
    	),
})
public class User {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String email;
	private String password;
	@Column(name = "first_name") private String firstName;
	@Column(name = "last_name") private String lastName;
	private int age;
	private int gender;
	private int role;
	@Column(name = "contact_number") private String contactNumber;
	@Column(name = "alternate_contact_number") private String alternateContactNumber;
	private String address;
	@Column(name = "city_code") private String cityCode;
	@Column(name = "state_code") private String stateCode;
	@Column(name = "country_code") private String countryCode;
	@Column(name = "create_time") private Timestamp createTime;
	@Column(name = "last_updated") private Timestamp lastUpdated;

}
