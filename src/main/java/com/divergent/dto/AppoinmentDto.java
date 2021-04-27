package com.divergent.dto;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.divergent.clinicmanagementsystem.ClinicManagementSystem;

@Component
public class AppoinmentDto {
	private static final Logger myLogger = LoggerFactory.getLogger(ClinicManagementSystem.class.getName());
	@Range(min = 1, message = "Please Enter Id Like 1.. ")
	private int appoinmentid;
	@Range(min = 1001, message = "Please Enter Id Like 1001.. ")
	private int patientid;
	@Range(min = 101, message = "Please Enter Id Like 101.. ")
	private int docid;
	@Size(min = 5, max = 25, message = "name should not be less than 5 and more than 25")
	private String docname;
	@Size(min = 5, max = 25, message = "name should not be less than 5 and more than 25")
	private String patientname;
	@NotNull
	private String problem;
	@DateTimeFormat
	private String date;
	@DateTimeFormat
	private String time;

	public int getAppoinmentid() {
		return appoinmentid;
	}

	public void setAppoinmentid(int appoinmentid) {
		this.appoinmentid = appoinmentid;
	}

	public int getPatientid() {
		return patientid;
	}

	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}

	public int getDocid() {
		return docid;
	}

	public void setDocid(int docid) {
		this.docid = docid;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public static boolean validator(AppoinmentDto appoinmentDto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<AppoinmentDto>> violations = validator.validate(appoinmentDto);
		for (ConstraintViolation<AppoinmentDto> violation : violations) {
			myLogger.error(violation.getMessage());
		}
		if (violations.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
