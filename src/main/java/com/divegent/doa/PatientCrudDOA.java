package com.divegent.doa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.divergent.clinicmanagementsystem.dto.PatientDto;
import com.divergent.databaseconnection.JDBCConnectionInterface;

public class PatientCrudDOA {
	JDBCConnectionInterface connectionInterface;

	public PatientCrudDOA(JDBCConnectionInterface connectionInterface) { 
		this.connectionInterface = connectionInterface;
	}

	public int create(int p_id, String p_name, int p_age, String p_gender, String p_contact, int p_weight,
			String p_address) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		int a = statement.executeUpdate("insert into patient values (" + p_id + ", '" + p_name + "'," + p_age + ", '"
				+ p_gender + "', '" + p_contact + "', " + p_weight + ", '" + p_address + "')");
		return a;
	}

	public int delete(int a) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		int b=statement.executeUpdate("delete from patient where p_id=" + a + "");
     return b;   
	}

	public List<PatientDto> read()throws SQLException{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from patient");
	
		List<PatientDto> list = new  ArrayList<>();
		
		while (resultSet.next()) {	
			PatientDto dto=new PatientDto();
			dto.setId(resultSet.getInt(1));
			dto.setName(resultSet.getString(2));
			dto.setAge(resultSet.getInt(3));
			dto.setGender(resultSet.getString(4));
			dto.setContact( resultSet.getString(5));
			dto.setWeight(resultSet.getInt(6));
			dto.setAddress(resultSet.getString(7));
			list.add(dto);
		}
		return list;
	}

	public int update(int rowid, String p_name, int p_age, String p_gender, String p_contact, int p_weight,
			String p_address) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		int b = statement.executeUpdate("update patient set p_name='" + p_name + "', p_age=" + p_age + ",p_gender ='"
				+ p_gender + "',p_contact='" + p_contact + "',p_weight=" + p_weight + ",p_address='" + p_address
				+ "' where p_id=" + rowid);
		return b;
	}

}
