package com.divergent.dto;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.divergent.clinicmanagementsystem.ClinicManagementSystem;

public class PatientDto {
	private static final Logger myLogger = LoggerFactory.getLogger(ClinicManagementSystem.class.getName());

	@Range(min = 1001, message = "Please Enter Id Like 1001.. ")
	private int id;
	@Min(value = 18, message = "age should not be less than 18")
	@Max(value = 50, message = "age should not be more than 100")
	private int age;
	@Min(value = 30, message = "Wieght should not be less than 30")
	private int weight;
	@Min(value = 5, message = " Name should not be less than 5 Character")
	@Max(value = 15, message = " Name should not be more than 15 Character")
	private String name;

	@Pattern(regexp = "^[M|F]{1}$", message = "Must be M or F")
	private String gender;
	@Min(value = 10, message = " Number less 10 digit Number")
	@Max(value = 10, message = " Number is more than 10 digit Number")
	private String contact;
	@Min(value = 5, message = " Address should not be less than 5")
	@Max(value = 30, message = " Address should not be more  than 45")
	private String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static Boolean validator(PatientDto patientDto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<PatientDto>> violations = validator.validate(patientDto);
		for (ConstraintViolation<PatientDto> violation : violations) {
			myLogger.error(violation.getMessage());
		}
		if (violations.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}
}
