  package com.divegent.doa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.divergent.clinicmanagementsystem.dto.DoctorDto;
import com.divergent.databaseconnection.JDBCConnectionInterface;

/*
 * This is Doctor DOA class here We will perform all crud operation
 */
public class DoctorCrudDOA {
	JDBCConnectionInterface connectionInterface;

	/**
	 * this is a constructor this will accept connection
	 * 
	 * @param connectionInterface
	 */
	public DoctorCrudDOA(JDBCConnectionInterface connectionInterface) {
		this.connectionInterface = connectionInterface;
	}

	public List<DoctorDto> read() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();

		resultSet = statement.executeQuery("select * from doctor");
		List<DoctorDto> doctorDtos = new ArrayList<>();
		while (resultSet.next()) {

			DoctorDto doctorDto = new DoctorDto();
			doctorDto.setId(resultSet.getInt(1));
			doctorDto.setUsername(resultSet.getString(2));
			doctorDto.setPassword(resultSet.getString(3));
			doctorDto.setName(resultSet.getString(4));
			doctorDto.setContact(resultSet.getString(5));
			doctorDto.setSpeciality(resultSet.getString(6));
			doctorDto.setFees(resultSet.getInt(7));
			doctorDtos.add(doctorDto);
		}
		return doctorDtos;
	}

	public int create(int doc_id, String doc_username, String doc_password, String doc_name, String doc_contact,
			String doc_speciality, int doc_fees) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		int a=statement.executeUpdate("insert into doctor values (" + doc_id + ", '" + doc_username + "','" + doc_password
				+ "', '" + doc_name + "', '" + doc_contact + "','" + doc_speciality + "'," + doc_fees + ")");
        return a;
	}

	public int update(int rowid,  String doc_username, String doc_password,String doc_name, String doc_contact,
			String doc_speciality, int doc_fees) throws SQLException {
		Connection connection = null;
		Statement statement = null;

		connection = connectionInterface.connection();
		statement = connection.createStatement();
	int a= statement.executeUpdate("update doctor set doc_name = '" + doc_name + "' ,doc_username = '" + doc_username
				+ "', doc_password = '" + doc_password + "', doc_contact = '" + doc_contact + "', doc_speciality = '"
				+ doc_speciality + "',doc_fees = " + doc_fees + " where doc_id = " + rowid);
		return  a;
	
	}

	public int delete(int a) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		int b=statement.executeUpdate("delete from doctor where doc_id=" + a + "");
	return b;
	}
}
