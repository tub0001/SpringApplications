package com.tadigital.dao;

import java.util.List;

import com.tadigital.entity.Vendor;

public interface VendorDao {
	public boolean insertVendor(Vendor vendor);
	public boolean selectvendor(Vendor vendor);
	public List<Vendor> selectAllVendors();
	public boolean editVendor(int vid);
	public boolean deleteVendor(int vid);
	boolean editVendor(int vid, String name, String email);
	Vendor selectvendorById(int vid);
}


