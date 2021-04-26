package com.divergent.dto;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.divergent.clinicmanagementsystem.ClinicManagementSystem;

@Component
public class DrugsDto {
	private static final Logger myLogger = LoggerFactory.getLogger(ClinicManagementSystem.class.getName());

	@Range(min = 2001, message = "Please Enter Id Like 2001.. ")
	private int drugsid;

	@Min(value = 5, message = " Name should not be less than 5")
	@Max(value = 15, message = " Name should not be more  than 15")
	private String drugsname;

	@Min(value = 5, message = " Drug Description should not be less than 5")
	@Max(value = 15, message = " Drug Description should not be more  than 15")
	private String drugsdescription;

	public int getDrugsid() {
		return drugsid;
	}

	public void setDrugsid(int drugsid) {
		this.drugsid = drugsid;
	}

	public String getDrugsname() {
		return drugsname;
	}

	public void setDrugsname(String drugsname) {
		this.drugsname = drugsname;
	}

	public String getDrugsdescription() {
		return drugsdescription;
	}

	public void setDrugsdescription(String drugsdescription) {
		this.drugsdescription = drugsdescription;
	}

	public static Boolean validator(DrugsDto drugsDto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<DrugsDto>> violations = validator.validate(drugsDto);
		for (ConstraintViolation<DrugsDto> violation : violations) {
			myLogger.error(violation.getMessage());
		}
		if (violations.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

}
