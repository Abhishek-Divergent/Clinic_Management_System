package com.divergent.clinicmanagementsystem.doa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/*
 * This is Doctor DOA class here We will perform all crud operation
 */
@Repository
public class DoctorCrudDOA {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> read() throws SQLException {
		List<Map<String, Object>> list = new ArrayList<>();
		list = jdbcTemplate.queryForList("select * from doctor");
		return list;
	}

	public int create(int doc_id, String doc_username, String doc_password, String doc_name, String doc_contact,
			String doc_speciality, int doc_fees) throws SQLException {
		return this.jdbcTemplate
				.update("insert into doctor values (" + doc_id + ", '" + doc_username + "','" + doc_password + "', '"
						+ doc_name + "', '" + doc_contact + "','" + doc_speciality + "'," + doc_fees + ")");
	}

	public int update(int rowid, String doc_username, String doc_password, String doc_name, String doc_contact,
			String doc_speciality, int doc_fees) throws SQLException {
		return this.jdbcTemplate.update("update doctor set doc_name = '" + doc_name + "' ,doc_username = '"
				+ doc_username + "', doc_password = '" + doc_password + "', doc_contact = '" + doc_contact
				+ "', doc_speciality = '" + doc_speciality + "',doc_fees = " + doc_fees + " where doc_id = " + rowid);
	}

	public int delete(int a) throws SQLException {
		return this.jdbcTemplate.update("delete from doctor where doc_id=" + a + "");
	}
}
