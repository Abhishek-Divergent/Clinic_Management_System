package com.divergent.clinicmanagementsystem.doa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * this patient DAO will contain method which will perfrom all CRUD operation
 * 
 * @author JAI MAHAKAL
 *
 */
@Repository
public class PatientCrudDOA {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int create(int p_id, String p_name, int p_age, String p_gender, String p_contact, int p_weight,
			String p_address) throws SQLException {
		return this.jdbcTemplate.update("insert into patient values (" + p_id + ", '" + p_name + "'," + p_age + ", '"
				+ p_gender + "', '" + p_contact + "', " + p_weight + ", '" + p_address + "')");
	}

	public int delete(int a) throws SQLException {
		return this.jdbcTemplate.update("delete from patient where p_id=" + a + "");
	}

	public List<Map<String, Object>> read() throws SQLException {
		List<Map<String, Object>> list = new ArrayList<>();
		list = jdbcTemplate.queryForList("select * from patient");
		return list;
	}

	public int update(int rowid, String p_name, int p_age, String p_gender, String p_contact, int p_weight,
			String p_address) throws SQLException {
		return this.jdbcTemplate.update("update patient set p_name='" + p_name + "', p_age=" + p_age + ",p_gender ='"
				+ p_gender + "',p_contact='" + p_contact + "',p_weight=" + p_weight + ",p_address='" + p_address
				+ "' where p_id=" + rowid);
	}

}
