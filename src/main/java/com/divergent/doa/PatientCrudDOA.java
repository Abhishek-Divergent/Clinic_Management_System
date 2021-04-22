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
/**
 * this patient DAO will contain  method which will perfrom all CRUD operation
 * @author JAI MAHAKAL
 *
 */
@Repository
public class PatientCrudDOA {
	@Autowired
	private JDBCConnectionInterface connectionInterface;
	public int create(int p_id, String p_name, int p_age, String p_gender, String p_contact, int p_weight,
			String p_address) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		return statement.executeUpdate("insert into patient values (" + p_id + ", '" + p_name + "'," + p_age + ", '"
				+ p_gender + "', '" + p_contact + "', " + p_weight + ", '" + p_address + "')");
	}

	public int delete(int a) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		return statement.executeUpdate("delete from patient where p_id=" + a + "");
		
	}

	public List<Map<Integer, String>> read() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from patient");

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

	public int update(int rowid, String p_name, int p_age, String p_gender, String p_contact, int p_weight,
			String p_address) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		return  statement.executeUpdate("update patient set p_name='" + p_name + "', p_age=" + p_age + ",p_gender ='"
				+ p_gender + "',p_contact='" + p_contact + "',p_weight=" + p_weight + ",p_address='" + p_address
				+ "' where p_id=" + rowid);
	}

}
