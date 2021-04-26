package com.divergent.dto;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.divergent.clinicmanagementsystem.ClinicManagementSystem;

@Component
public class LabTestDto {
	private static final Logger myLogger = LoggerFactory.getLogger(ClinicManagementSystem.class.getName());

	@Range(min = 1001, message = "Please Enter Id Like 1001.. ")
	private int labtestid;

	@Range(min = 250, message = "minimum price is of labtest is 250 ")
	private int labtest_price;
	@Min(value = 5, message = "Lab Test Name should not be less than 5 Character")
	// @Max(value = 15, message = " Name should not be more than 15 Character")
	private String labtest_name;

	public int getLabtestid() {
		return labtestid;
	}

	public void setLabtestid(int labtestid) {
		this.labtestid = labtestid;
	}

	public int getLabtest_price() {
		return labtest_price;
	}

	public void setLabtest_price(int labtest_price) {
		this.labtest_price = labtest_price;
	}

	public String getLabtest_name() {
		return labtest_name;
	}

	public void setLabtest_name(String labtest_name) {
		this.labtest_name = labtest_name;
	}

	public static Boolean validator(LabTestDto labTestDto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<LabTestDto>> violations = validator.validate(labTestDto);
		for (ConstraintViolation<LabTestDto> violation : violations) {
			myLogger.error(violation.getMessage());
		}
		if (violations.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
