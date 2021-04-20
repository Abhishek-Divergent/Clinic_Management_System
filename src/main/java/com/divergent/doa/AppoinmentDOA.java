package com.divergent.doa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.divergent.databaseconnection.JDBCConnectionInterface;
import com.divergent.dto.AppoinmentDto;

public class AppoinmentDOA {

	private JDBCConnectionInterface connectionInterface;

	public AppoinmentDOA(JDBCConnectionInterface connectionInterface) {
		super();
		this.connectionInterface = connectionInterface;
	}

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

	public List<AppoinmentDto> read() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select *from appoinment");
		List<AppoinmentDto> list = new ArrayList<>();
		while (resultSet.next()) {
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
			AppoinmentDto appoinmentDto = (AppoinmentDto) context.getBean("appoinmentdtoid");
			appoinmentDto.setAppoinmentid(resultSet.getString(1));
			appoinmentDto.setPatientid(resultSet.getString(2));
			appoinmentDto.setDocid(resultSet.getString(3));
			appoinmentDto.setDocname(resultSet.getString(4));
			appoinmentDto.setPatientname(resultSet.getString(5));
			appoinmentDto.setProblem(resultSet.getString(6));
			appoinmentDto.setDate(resultSet.getString(7));
			appoinmentDto.setTime(resultSet.getString(8));
			list.add(appoinmentDto);
		}
		return list;
	}
}
