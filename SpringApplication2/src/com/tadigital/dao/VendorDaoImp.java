package com.tadigital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.tadigital.entity.Test;

@Component
public class VendorDaoImp implements VendorDao{
	public Boolean loginProcess(Test t) {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			stmt = con.createStatement();
			String sql = "SELECT * FROM test1 WHERE Email = '" + t.getName() + "' AND Password = '" + t.getPassword() + "'";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
			return false;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try {
					if(stmt != null) {
						stmt.close();
					}
					if(con != null) {
						con.close();
					}
				} catch (SQLException e) {
						e.printStackTrace();
				}
			}
		return false;
	}
}
