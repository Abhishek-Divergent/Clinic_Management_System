  package com.divergent.doa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.divergent.databaseconnection.JDBCConnectionInterface;
import com.divergent.dto.DoctorDto;

/*
 * This is Doctor DOA class here We will perform all crud operation
 */
public class DoctorCrudDOA {
	private JDBCConnectionInterface connectionInterface;
	public DoctorCrudDOA(JDBCConnectionInterface connectionInterface) {
		super();
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
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
			DoctorDto doctorDto =(DoctorDto) context.getBean("doctordtoid");
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
