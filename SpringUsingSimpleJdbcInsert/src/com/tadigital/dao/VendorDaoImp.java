package com.tadigital.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.tadigital.entity.Vendor;

@Repository
public class VendorDaoImp implements VendorDao{
	private NamedParameterJdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert simpleJdbc;
	
	/*@Autowired
	public VendorDaoImp(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}*/
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		simpleJdbc = new SimpleJdbcInsert(dataSource);
		simpleJdbc.withTableName("test1");
		simpleJdbc.usingGeneratedKeyColumns("id");
	}
	
	@Override
	public boolean loginProcess(Vendor v) {
		String sql = "SELECT * FROM test1 WHERE Email = :uname AND Password = :password";
		Vendor ven = new Vendor();
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(v);
		try {
			ven = jdbcTemplate.queryForObject(sql, parameters, new RowMapper<Vendor>() {
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
		//String sql = "INSERT INTO test1(Name,Email,Password) VALUES(:name,:uname,:password);";
		
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(v);
		
		int rows = simpleJdbc.execute(parameters);
		
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
		String sql = "DELETE from test1 WHERE Id = :id";
		
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(vid);
		
		
		int rows = jdbcTemplate.update(sql, parameters);
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
		String sql = "SELECT * FROM test1 WHERE Id = :id";
		Vendor v = new Vendor();
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(vid);
		try {
			v = jdbcTemplate.queryForObject(sql, parameters, new RowMapper<Vendor>() {
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
		String sql = "UPDATE test1 SET Name= :name, Email = :email WHERE Id= :id";
		
		BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(v);
		
		int rows = jdbcTemplate.update(sql, parameters);
		
		return rows != 0;
	}
}
