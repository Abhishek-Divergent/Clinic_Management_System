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

/*
 * This is Doctor DOA class here We will perform all crud operation
 */
@Repository
public class DoctorCrudDOA {
	@Autowired
	private JDBCConnectionInterface connectionInterface;
	
	public List<Map<Integer, String>> read() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();

		resultSet = statement.executeQuery("select * from doctor");
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
			list.add(map);
		}
		return list;
	}

	public int create(int doc_id, String doc_username, String doc_password, String doc_name, String doc_contact,
			String doc_speciality, int doc_fees) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		return statement.executeUpdate("insert into doctor values (" + doc_id + ", '" + doc_username + "','" + doc_password
				+ "', '" + doc_name + "', '" + doc_contact + "','" + doc_speciality + "'," + doc_fees + ")");
	}

	public int update(int rowid,  String doc_username, String doc_password,String doc_name, String doc_contact,
			String doc_speciality, int doc_fees) throws SQLException {
		Connection connection = null;
		Statement statement = null;

		connection = connectionInterface.connection(); 
		statement = connection.createStatement();
		return  statement.executeUpdate("update doctor set doc_name = '" + doc_name + "' ,doc_username = '" + doc_username
				+ "', doc_password = '" + doc_password + "', doc_contact = '" + doc_contact + "', doc_speciality = '"
				+ doc_speciality + "',doc_fees = " + doc_fees + " where doc_id = " + rowid);
	}

	public int delete(int a) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		return statement.executeUpdate("delete from doctor where doc_id=" + a + "");
	}
}
