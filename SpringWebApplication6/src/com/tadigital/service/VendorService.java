package com.tadigital.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tadigital.dao.VendorDao;
import com.tadigital.dao.VendorDaoImpl;
import com.tadigital.entity.Vendor;

@Service
public class VendorService implements VendorServiceImp {
	VendorDao vendorDao;

	@Autowired
	public VendorService(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}

	@Override
	public boolean insertVendor(Vendor vendor) {
		if (vendorDao.insertVendor(vendor)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean selectVendor(Vendor vendor) {
		if (vendorDao.selectvendor(vendor)) {
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Vendor> selectAllUsers() {
		ArrayList<Vendor> list = new ArrayList<>();
		list = (ArrayList<Vendor>) vendorDao.selectAllVendors();
		return list;
	}
  @Override
	public Vendor selectVendorById(int vid) {
		Vendor vendor=new Vendor();
		vendor=vendorDao.selectvendorById(vid);
		return vendor;
	}

public void editVendor(int vid, String name, String email) {
	
	vendorDao.editVendor(vid, name, email);
	
}
}
