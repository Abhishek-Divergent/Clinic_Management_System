package com.divergent.dto;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.divergent.clinicmanagementsystem.ClinicManagementSystem;

@Component
public class DoctorDto {
	private static final Logger myLogger = LoggerFactory.getLogger(ClinicManagementSystem.class.getName());
	@NotNull(message = "Enter Username/Email")
	private String username;
	
    @Range(min=6, message = "Please Enter password more than 6 digit")
	private String password;
	
    @Min(value = 5, message = " Name should not be less than 5 Character")
	@Max(value = 15, message = " Name should not be more than 15 Character")
	private String name;
	
    @Min(value = 10, message = " Number less 10 digit Number")
	@Max(value = 10, message = " Number is more than 10 digit Number")
	private String contact;

	@Min(value = 5, message = "  speciality should not be less than 5")
	@Max(value = 15, message = " speciality should not be more  than 15")
	private String speciality;
	
	@Range(min = 101, message = "Please Enter Id Like 101.. ")
	private int id;

	@NotNull(message = " Fees cant be zero")
	private int fees;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFees() {
		return fees;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}
	public static Boolean validator(DoctorDto doctorDto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<DoctorDto>> violations = validator.validate(doctorDto);
		for (ConstraintViolation<DoctorDto> violation : violations) {
			myLogger.error(violation.getMessage());
		}
		if (violations.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
}
