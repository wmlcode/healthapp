package ru.lanit.healthapp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private int gender;
    private int role;
    private String contactNumber;
    private String alternateContactNumber;
    private String address;
    private String cityCode;
    private String stateCode;
    private String countryCode;
    private String specialityCode;
}
