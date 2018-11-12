package com.tadigital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "customer")
@DynamicInsert(value = true)
public class Customer {
	
	@Id
	@Column(name="cust_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int custId;
	@Column(name="cust_name")
	String custName;
	@Column(name="cust_lastname")
	String custLastName;
	@Column(name="cust_email")
	String custEmail;
	
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustLastName() {
		return custLastName;
	}
	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
}
