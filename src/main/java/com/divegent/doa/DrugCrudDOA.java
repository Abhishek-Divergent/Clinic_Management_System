package com.divegent.doa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.divergent.clinicmanagementsystem.dto.DrugsDto;
import com.divergent.databaseconnection.JDBCConnectionInterface;

public class DrugCrudDOA {
	JDBCConnectionInterface connectionInterface;

	public DrugCrudDOA(JDBCConnectionInterface connectionInterface) {
		this.connectionInterface = connectionInterface;
	}

	public int delete(int a) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
	  return	statement.executeUpdate("delete from drugs where drugs_id=" + a + "");
	}

	public int create(int drugs_id, String drugs_name, String drugs_description) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
	return	statement.executeUpdate(
				"insert into drugs  values (" + drugs_id + ", '" + drugs_name + "','" + drugs_description + "')");
	}

	public List<DrugsDto> read() throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet;
		connection = connectionInterface.connection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select *from drugs");
		List<DrugsDto> list = new ArrayList<>();
		while (resultSet.next()) {
			DrugsDto drugsDto = new DrugsDto();
			drugsDto.setDrugsid(resultSet.getInt(1));
			drugsDto.setDrugsname(resultSet.getString(2));
			drugsDto.setDrugsdescription(resultSet.getString(3));
			list.add(drugsDto);
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
