package com.divergent.dto;

import org.springframework.stereotype.Component;

@Component
public class AppoinmentDto {
	private String appoinmentid, patientid, docid;
	private String docname, patientname, problem, date, time;

	public String getAppoinmentid() {
		return appoinmentid;
	}

	public void setAppoinmentid(String appoinmentid) {
		this.appoinmentid = appoinmentid;
	}

	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
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

}
