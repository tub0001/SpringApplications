package com.tadigital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tadigital.dao.VendorDao;
import com.tadigital.entity.Test;

@Component
public class ServiceImpl implements Service {
	
	private VendorDao vendordao;
	
	@Autowired
	public ServiceImpl(VendorDao vendordao) {
		this.vendordao = vendordao;
	}


	public Boolean loginVendor(Test t) {
		return vendordao.loginProcess(t);
	}
}
