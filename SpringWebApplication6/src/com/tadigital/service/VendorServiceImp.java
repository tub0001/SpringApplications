package com.tadigital.service;

import java.util.ArrayList;

import com.tadigital.entity.Vendor;

public interface VendorServiceImp {
	
	public boolean insertVendor(Vendor vendor);
	public boolean selectVendor(Vendor vendor);
	public ArrayList<Vendor> selectAllUsers();
	public Vendor selectVendorById(int vid);
	
}
