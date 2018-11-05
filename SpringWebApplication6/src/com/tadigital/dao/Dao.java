
package com.tadigital.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;


public class Dao {

	
	private static DataSource dataSource = null;
	static {
		Properties properties = new Properties();
		
		InputStream inputStream = Dao.class.getClassLoader().getResourceAsStream("db.properties");
		
		try {
			properties.load(inputStream);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
		GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
		connectionPool.setMaxTotal(5);
		poolableConnectionFactory.setPool(connectionPool);
		
		PoolingDataSource<PoolableConnection> poolingDataSource = new PoolingDataSource<>(connectionPool);
		dataSource = poolingDataSource;
	}
	
	protected static DataSource getDataSource() {
		return dataSource;	
	}
	
	protected static void shutdownDataSource() {
		PoolingDataSource<PoolableConnection> poolingDataSource = (PoolingDataSource) dataSource;
		try {
			poolingDataSource.close();
		} catch (Exception e) {
		
		}
	}
	
	protected void closeConnection(Connection con) {
		if(con != null) {
			try {
				con.close();
			} catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
	
	protected void closeStatement(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
	
	protected void closePreparedStatement(PreparedStatement pStmt) {
		if(pStmt != null) {
			try {
				pStmt.close();
			} catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
	
	protected void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
}