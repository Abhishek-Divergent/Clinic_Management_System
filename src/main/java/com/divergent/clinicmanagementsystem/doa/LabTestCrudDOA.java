package com.divergent.clinicmanagementsystem.doa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LabTestCrudDOA {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int delete(int a) throws SQLException {
		return this.jdbcTemplate.update("delete from LabTest where labtest_id=" + a + "");
	}

	public int create(int labtest_id, String labtest_name, int labtest_price) throws SQLException {
		return this.jdbcTemplate.update(
				"insert into labtest  values (" + labtest_id + ", '" + labtest_name + "','" + labtest_price + "')");
	}

	public List<Map<String, Object>> read() throws SQLException {
		List<Map<String, Object>> list = new ArrayList<>();
		list = jdbcTemplate.queryForList("select *from labtest");
		return list;
	}

	public int update(int rowid, String labtest_name, int labtest_price) throws SQLException {
		return this.jdbcTemplate.update("update labtest set labtest_name='" + labtest_name + "',labtest_price="
				+ labtest_price + " where labtest_id=" + rowid);
	}

}
