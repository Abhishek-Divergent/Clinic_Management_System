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
import com.divergent.dto.LabTestDto;

public class LabTestCrudDOA {

	private JDBCConnectionInterface connectionInterface;

	public void setConnectionInterface(JDBCConnectionInterface connectionInterface) {
		this.connectionInterface = connectionInterface;
	}

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

	public List<LabTestDto> read() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select *from labtest");
		List<LabTestDto> list = new ArrayList<>();
		while (resultSet.next()) {
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
			LabTestDto dto = (LabTestDto) context.getBean("labtestdtoid");
			dto.setLabtestid(resultSet.getInt(1));
			dto.setLabtest_name(resultSet.getString(2));
			dto.setLabtest_price(resultSet.getInt(3));
			list.add(dto);
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
