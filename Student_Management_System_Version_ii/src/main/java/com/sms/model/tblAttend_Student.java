package com.sms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblattendstudent")
public class tblAttend_Student 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String attendcoursename;
	private String attendusername;
	private int attenddepartmentid;
	
	public tblAttend_Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public tblAttend_Student(String attendcoursename, String attendusername, int attenddepartmentid) {
		super();
		this.attendcoursename = attendcoursename;
		this.attendusername = attendusername;
		this.attenddepartmentid = attenddepartmentid;
	}
	@Override
	public String toString() {
		return "tblAttend_Student [id=" + id + ", attendcoursename=" + attendcoursename + ", attendusername="
				+ attendusername + ", attenddepartmentid=" + attenddepartmentid + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAttendcoursename() {
		return attendcoursename;
	}
	public void setAttendcoursename(String attendcoursename) {
		this.attendcoursename = attendcoursename;
	}
	public String getAttendusername() {
		return attendusername;
	}
	public void setAttendusername(String attendusername) {
		this.attendusername = attendusername;
	}
	public int getAttenddepartmentid() {
		return attenddepartmentid;
	}
	public void setAttenddepartmentid(int attenddepartmentid) {
		this.attenddepartmentid = attenddepartmentid;
	}
	
	
}
