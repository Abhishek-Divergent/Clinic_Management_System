package com.divergent.clinicmanagementsystem.doa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DrugCrudDOA {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int delete(int a) throws SQLException {
		return this.jdbcTemplate.update("delete from drugs where drugs_id=" + a + "");
	}

	public int create(int drugs_id, String drugs_name, String drugs_description) throws SQLException {
		return this.jdbcTemplate.update(
				"insert into drugs  values (" + drugs_id + ", '" + drugs_name + "','" + drugs_description + "')");
	}

	public List<Map<String, Object>> read() throws SQLException {
		List<Map<String, Object>> list = new ArrayList<>();
		list = jdbcTemplate.queryForList("select *from drugs");
		return list;
	}

	public int update(int rowid, String drugs_name, String drugs_description) throws SQLException {
		return this.jdbcTemplate.update("update drugs set drugs_name='" + drugs_name + "',drugs_description='"
				+ drugs_description + "' where drugs_id=" + rowid);
	}

}
