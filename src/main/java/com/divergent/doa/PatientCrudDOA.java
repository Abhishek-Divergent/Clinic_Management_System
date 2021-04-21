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
import com.divergent.dto.PatientDto;
/**
 * this patient DAO will contain  method which will perfrom all CRUD operation
 * @author JAI MAHAKAL
 *
 */
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

	public List<PatientDto> read() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from patient");

		List<PatientDto> list = new ArrayList<>();

		while (resultSet.next()) {
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
			PatientDto patientDto=(PatientDto) context.getBean("patientDto");
			 patientDto.setId(resultSet.getInt(1));
			 patientDto.setName(resultSet.getString(2));
			 patientDto.setAge(resultSet.getInt(3));
			 patientDto.setGender(resultSet.getString(4));
			 patientDto.setContact(resultSet.getString(5));
			 patientDto.setWeight(resultSet.getInt(6));
			 patientDto.setAddress(resultSet.getString(7));
			list.add(patientDto);
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
