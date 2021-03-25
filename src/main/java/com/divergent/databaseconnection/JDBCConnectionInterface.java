package com.divergent.databaseconnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * {@link JDBCConnectionInterface} implements on {@link JDBCConnection} There is
 * connection method that is in {@link JDBCConnection} class
 * 
 * @author JAI MAHAKAL
 *
 */
public interface JDBCConnectionInterface {

	public Connection connection() throws SQLException;

}
