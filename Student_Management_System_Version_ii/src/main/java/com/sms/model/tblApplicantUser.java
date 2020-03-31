package com.sms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblapplicantuser")
public class tblApplicantUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstname;
	private String lastname;
	private String gender;
	private String birthdate;
	private String address;
	private String mobile;
	private String email;
	
	public tblApplicantUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public tblApplicantUser(String firstname, String lastname, String gender, String birthdate, String address,
			String mobile, String email) 
	{
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.address = address;
		this.mobile = mobile;
		this.email = email;
	}
	@Override
	public String toString() {
		return "tblApplicantUser [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", gender="
				+ gender + ", birthdate=" + birthdate + ", address=" + address + ", mobile=" + mobile + ", email="
				+ email + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
