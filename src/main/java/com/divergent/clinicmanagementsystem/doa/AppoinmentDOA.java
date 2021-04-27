package com.divergent.clinicmanagementsystem.doa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppoinmentDOA {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int create(int appoiment_id, int doc_id, int patient_id, String doc_name, String patient_name,
			String problem, String date, String time) throws SQLException {

		return this.jdbcTemplate
				.update("insert into appoinment values( " + appoiment_id + "," + doc_id + "," + patient_id + ", '"
						+ doc_name + "','" + patient_name + "','" + problem + "','" + date + "','" + time + "')");
	}

	public int delete(int a) throws SQLException {
		return this.jdbcTemplate.update("delete from  appoinment where appoinment_id=" + a + " ");
	}

	public List<Map<String, Object>> read() throws SQLException {

		List<Map<String, Object>> list = new ArrayList<>();
		list = jdbcTemplate.queryForList("select *from appoinment");
		return list;
	}
}
