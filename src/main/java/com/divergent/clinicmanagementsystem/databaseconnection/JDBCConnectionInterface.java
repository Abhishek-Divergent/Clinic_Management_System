package com.divergent.clinicmanagementsystem.databaseconnection;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

/**
 * {@link JDBCConnectionInterface} implements on {@link JDBCConnection} There is
 * connection method that is in {@link JDBCConnection} class
 * 
 * @author JAI MAHAKAL
 *
 */
@Repository
public interface JDBCConnectionInterface {
	public Connection connection() throws SQLException;
}
