package com.divergent.dto;

import org.springframework.stereotype.Component;

@Component
public class LabTestDto {
	private int labtestid, labtest_price;
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

}
