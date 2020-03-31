package com.sms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblenrollcourse")
public class tblEnrollCourse 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int department_id;
	private String enrollcoursename;
	private String enrollusername;
	
	public tblEnrollCourse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public tblEnrollCourse( int department_id, String enrollcoursename, String enrollusername) {
		super();
		this.department_id = department_id;
		this.enrollcoursename = enrollcoursename;
		this.enrollusername = enrollusername;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public String getEnrollcoursename() {
		return enrollcoursename;
	}
	public void setEnrollcoursename(String enrollcoursename) {
		this.enrollcoursename = enrollcoursename;
	}
	public String getEnrollusername() {
		return enrollusername;
	}
	public void setEnrollusername(String enrollusername) {
		this.enrollusername = enrollusername;
	}
	@Override
	public String toString() {
		return "tblEnrollCourse [id=" + id + ", department_id=" + department_id + ", enrollcoursename="
				+ enrollcoursename + ", enrollusername=" + enrollusername + "]";
	}

	
	
}
