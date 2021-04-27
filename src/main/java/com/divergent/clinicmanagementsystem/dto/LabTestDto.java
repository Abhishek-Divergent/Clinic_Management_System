package com.divergent.clinicmanagementsystem.dto;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.divergent.clinicmanagementsystem.application.ClinicManagementSystem;

@Component
public class LabTestDto {
	private static final Logger myLogger = LoggerFactory.getLogger(ClinicManagementSystem.class.getName());

	@Range(min = 1001, message = "Please Enter Id Like 1001.. ")
	private int labtestid;
   
	@NotNull
	private int labtest_price;
	
	@Size(min = 5, max = 25, message = "name should not be less than 5 and more than 25")
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
