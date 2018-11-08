package com.tadigital.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tadigital.entity.Vendor;

@Repository
public class VendorDaoImp implements VendorDao{
	private JdbcTemplate jdbcTemplate;
	
	/*@Autowired
	public VendorDaoImp(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}*/
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean loginProcess(Vendor v) {
		String sql = "SELECT * FROM test1 WHERE Email = ? AND Password = ?";
		Vendor ven = new Vendor();
		try {
			ven = jdbcTemplate.queryForObject(sql, new Object[] {v.getUname(), v.getPassword()}, new RowMapper<Vendor>() {
																						@Override
																						public Vendor mapRow(ResultSet rs, int rowNum) throws SQLException {
																							Vendor vendor = new Vendor();
																							vendor.setId(rs.getInt(1));
																							vendor.setName(rs.getString(2));
																							vendor.setUname(rs.getString(3));
																							vendor.setPassword(rs.getString(4));
																							return vendor;
																						}
			});
		} catch(EmptyResultDataAccessException erdae) {
			erdae.printStackTrace();
		}
		
		return ven!=null;
	}
	
	public boolean registerVendor(Vendor v) {
		String sql = "INSERT INTO test1(Name,Email,Password) VALUES(?,?,?);";
		
		int rows = jdbcTemplate.update(sql,v.getName(),v.getUname(),v.getPassword());
		
		return rows!= 0;
	}

	@Override
	public List<Vendor> getVendors() {
		String sql = "SELECT * FROM test1";
			
		return jdbcTemplate.query(sql, new RowMapper<Vendor>() {
			@Override
			public Vendor mapRow(ResultSet rs, int rowNum) throws SQLException {
				Vendor v = new Vendor();
				v.setId(rs.getInt(1));
				v.setName(rs.getString(2));
				v.setUname(rs.getString(3));
				v.setPassword(rs.getString(4));
				return v;
			}
		});
	}

	@Override
	public List<Vendor> deleteVendor(int vid) {
		String sql = "DELETE from test1 WHERE Id = ?";
		
		int rows = jdbcTemplate.update(sql, vid);
		if(rows != 0) {
			sql = "SELECT * FROM test1";
			return jdbcTemplate.query(sql, new RowMapper<Vendor>() {
				public Vendor mapRow(ResultSet rs, int rowNum) throws SQLException {
					Vendor v = new Vendor();
					v.setId(rs.getInt(1));
					v.setName(rs.getString(2));
					v.setUname(rs.getString(3));
					v.setPassword(rs.getString(4));
					return v;
				}
			});
		}
		return null;
	}

	@Override
	public Vendor editVendor(int vid) {
		String sql = "SELECT * FROM test1 WHERE Id = ?";
		Vendor v = new Vendor();
		try {
			v = jdbcTemplate.queryForObject(sql, new Object[] {vid}, new RowMapper<Vendor>() {
				@Override
				public Vendor mapRow(ResultSet rs, int numRow) throws SQLException {
					Vendor vendor = new Vendor();
					vendor.setId(rs.getInt(1));
					vendor.setName(rs.getString(2));
					vendor.setUname(rs.getString(3));
					vendor.setPassword(rs.getString(4));
					return vendor;
				}
			});
		}catch(EmptyResultDataAccessException erdae) {
			erdae.printStackTrace();
		}
		return v;
	}

	@Override
	public boolean updateVendor(Vendor v) {
		String sql = "UPDATE test1 SET Name= ?, Email = ? WHERE Id= ?";
		
		int rows = jdbcTemplate.update(sql, v.getName(), v.getUname(), v.getId());
		
		return rows != 0;
	}
}
