package com.tadigital.entity;

import javax.servlet.http.Part;

public class Vendor {
	
	int id;
	String name;
	String uname;
	String password;
	String imgName;
	Part imgPart;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public Part getImgPart() {
		return imgPart;
	}
	public void setImgPart(Part imgPart) {
		this.imgPart = imgPart;
	}
	
	
}
