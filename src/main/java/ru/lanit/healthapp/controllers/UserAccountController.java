package ru.lanit.healthapp.controllers;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ru.lanit.healthapp.dto.UserDTO;
import ru.lanit.healthapp.model.User;
import ru.lanit.healthapp.helpers.ExecutionStatus;
import ru.lanit.healthapp.services.DoctorService;
import ru.lanit.healthapp.services.UserService;

@RestController
@RequestMapping("/account/*")
public class UserAccountController {
	
	final static Logger logger = LoggerFactory.getLogger(UserAccountController.class);
	
	UserService userService;
	DoctorService docService;
	
	@Autowired
	public UserAccountController(UserService userService, DoctorService docService) {
		this.userService = userService;
		this.docService = docService;
	}
	
	@GetMapping(value="/token")
	public Map<String, String> getToken(HttpSession session) {
		return Collections.singletonMap("token", session.getId());
	}
	
	@PostMapping(value="/signup")
	public ExecutionStatus processSignup(ModelMap model, @RequestBody UserDTO reqUser) {
		User user = null;
		try {
			user = userService.doesUserExist(reqUser.getEmail());
		} catch (Exception e) {
		}
		if(user != null) {
			return new ExecutionStatus("USER_ACCOUNT_EXISTS", "User account with same email address exists. Please try again!");
		}
		user = new User();
		user.setEmail(reqUser.getEmail());
		user.setPassword(reqUser.getPassword());
		user.setFirstName(reqUser.getFirstName());
		user.setLastName(reqUser.getLastName());
		user.setContactNumber(reqUser.getContactNumber());
		user.setAlternateContactNumber(reqUser.getAlternateContactNumber());
		user.setCityCode(reqUser.getCityCode());
		user.setStateCode(reqUser.getStateCode());
		user.setCountryCode(reqUser.getCountryCode());
		user.setAge(reqUser.getAge());
		user.setGender(reqUser.getGender());
		user.setRole(reqUser.getRole());
		userService.save(user);

		// If user is a doctor?
		if (user.getRole() == 1) {
			docService.addDoctor(user, reqUser.getSpecialityCode());
		}
		
		return new ExecutionStatus("USER_ACCOUNT_CREATED", "User account successfully created");
	}
	
	@PostMapping(value="/user/update")
	public ExecutionStatus updateUser(ModelMap model, @RequestBody User reqUser) {
		User user = new User();
		user.setId(reqUser.getId());
		user.setPassword(reqUser.getPassword());
		user.setEmail(reqUser.getEmail());
		user.setFirstName(reqUser.getFirstName());
		user.setLastName(reqUser.getLastName());
		user.setContactNumber(reqUser.getContactNumber());
		user.setAlternateContactNumber(reqUser.getAlternateContactNumber());
		user.setCityCode(reqUser.getCityCode());
		user.setStateCode(reqUser.getStateCode());
		user.setCountryCode(reqUser.getCountryCode());
		user.setAge(reqUser.getAge());
		user.setGender(reqUser.getGender());
		userService.update(user);
		return new ExecutionStatus("USER_ACCOUNT_UPDATED", "User account successfully updated");
	}

	@PostMapping(value="/update", produces="application/json")
	public ModelAndView updateProfile(ModelMap model, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("address") String address, 
			@RequestParam("contact_number") String contactNumber) {
		return new ModelAndView("update", model);
	}
	
	@PostMapping(value="/forgotpassword/process", produces="application/json")
	public ModelAndView processForgotPassword(ModelMap model, @RequestParam("emailaddress") String email) {
		
		User user = null;
		try {
			user = userService.doesUserExist(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(user != null) {
			
		}	
		model.addAttribute("message", "An email notification is sent to the registered email address.");
		return new ModelAndView("forgotpassword", model);
	}
}
