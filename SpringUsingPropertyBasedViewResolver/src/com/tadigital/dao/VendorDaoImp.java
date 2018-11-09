package com.tadigital.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tadigital.entity.Vendor;

@Repository
public class VendorDaoImp implements VendorDao{
	private NamedParameterJdbcTemplate npjt;
	
	/*@Autowired
	public VendorDaoImp(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}*/
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		npjt = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public boolean loginProcess(Vendor v) {
		String sql = "SELECT * FROM test1 WHERE Email = :email AND password = :password";
		Vendor ven = null;
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("email", v.getUname());
		parameters.addValue("password", v.getPassword());
		try {
			ven = npjt.queryForObject(sql, parameters, new RowMapper<Vendor>() {
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
		String sql = "INSERT INTO test1(Name,Email,Password,Profile) VALUES(:name,:email,:password,:imgName);";
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", v.getName());
		parameters.addValue("email", v.getUname());
		parameters.addValue("password", v.getPassword());
		parameters.addValue("imgName", v.getImgName());
		
		int rows = npjt.update(sql, parameters);
		
		return rows!= 0;
	}

	@Override
	public List<Vendor> getVendors() {
		String sql = "SELECT * FROM test1";
			
		return npjt.query(sql, new RowMapper<Vendor>() {
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
		String sql = "DELETE from test1 WHERE id = :id";
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", vid);
		
		int rows = npjt.update(sql, parameters);
		if(rows != 0) {
			sql = "SELECT * FROM user_info";
			return npjt.query(sql, new RowMapper<Vendor>() {
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
		String sql = "SELECT * FROM test1 WHERE id = :id";
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", vid);
		
		Vendor v = new Vendor();
		try {
			v = npjt.queryForObject(sql, parameters, new RowMapper<Vendor>() {
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
		String sql = "UPDATE test1 SET Name= :name, Email = :email WHERE id= :id";
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("name", v.getName());
		parameters.addValue("email", v.getUname());
		parameters.addValue("id", v.getId());
		
		int rows = npjt.update(sql, parameters);
		
		return rows != 0;
	}
}