package com.divergent.doa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.divergent.databaseconnection.JDBCConnectionInterface;

@Repository
public class LabTestCrudDOA {
	@Autowired
	private JDBCConnectionInterface connectionInterface;

	public int delete(int a) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		return statement.executeUpdate("delete from LabTest where labtest_id=" + a + "");
	}

	public int create(int labtest_id, String labtest_name, int labtest_price) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		return statement.executeUpdate(
				"insert into labtest  values (" + labtest_id + ", '" + labtest_name + "','" + labtest_price + "')");
	}

	public List<Map<Integer, String>> read() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select *from labtest");
		List<Map<Integer, String>> list = new ArrayList<>();

		while (resultSet.next()) {
			Map<Integer, String> map = new HashMap<>();
			map.put(1, resultSet.getString(1));
			map.put(2, resultSet.getString(2));
			map.put(3, resultSet.getString(3));
			list.add(map);
		}
		return list;
	}

	public int update(int rowid, String labtest_name, int labtest_price) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		return statement.executeUpdate("update labtest set labtest_name='" + labtest_name + "',labtest_price="
				+ labtest_price + " where labtest_id=" + rowid);
	}

}
