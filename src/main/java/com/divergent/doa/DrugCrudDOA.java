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
public class DrugCrudDOA {
	@Autowired
	private JDBCConnectionInterface connectionInterface;

	public int delete(int a) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		return statement.executeUpdate("delete from drugs where drugs_id=" + a + "");
	}

	public int create(int drugs_id, String drugs_name, String drugs_description) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		return statement.executeUpdate(
				"insert into drugs  values (" + drugs_id + ", '" + drugs_name + "','" + drugs_description + "')");
	}

	public List<Map<Integer, String>> read() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select *from drugs");
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

	public int update(int rowid, String drugs_name, String drugs_description) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		return statement.executeUpdate("update drugs set drugs_name='" + drugs_name + "',drugs_description='"
				+ drugs_description + "' where drugs_id=" + rowid);
	}

}
