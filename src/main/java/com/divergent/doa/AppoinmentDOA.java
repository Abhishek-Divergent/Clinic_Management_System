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
public class AppoinmentDOA {
	@Autowired
	private JDBCConnectionInterface connectionInterface;

	public int create(int appoiment_id, int doc_id, int patient_id, String doc_name, String patient_name,
			String problem, String date, String time) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		return statement.executeUpdate(
				"insert into appoinment values( " + appoiment_id + "," + doc_id + "," + patient_id + ", '" + doc_name
						+ "','" + patient_name + "','" + problem + "','" + date + "','" + time + "')");
	}

	public int delete(int a) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		return statement.executeUpdate("delete from  appoinment where appoinment_id=" + a + "");
	}

	public List<Map<Integer, String>> read() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select *from appoinment");
		List<Map<Integer, String>> list = new ArrayList<>();

		while (resultSet.next()) {
			Map<Integer, String> map = new HashMap<>();
			map.put(1, resultSet.getString(1));
			map.put(2, resultSet.getString(2));
			map.put(3, resultSet.getString(3));
			map.put(4, resultSet.getString(4));
			map.put(5, resultSet.getString(5));
			map.put(6, resultSet.getString(6));
			map.put(7, resultSet.getString(7));
			map.put(8, resultSet.getString(8));
			list.add(map);
		}
		return list;
	}
}
