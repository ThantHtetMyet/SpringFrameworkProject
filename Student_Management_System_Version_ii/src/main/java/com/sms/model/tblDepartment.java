package com.sms.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbldepartment")
public class tblDepartment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private int departmentid;
private String firstname;

@OneToMany(mappedBy="tblcoursetbldepartment",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<tblCourses> tblcoursetbldepartment;

@OneToMany(mappedBy="tblfaculty_tbldepartment")
private List<tblFaculty> tblfaculty_tbldepartment;


public List<tblCourses> getTblcoursetbldepartment() {
	return tblcoursetbldepartment;
}

public void setTblcoursetbldepartment(List<tblCourses> tblcoursetbldepartment) {
	this.tblcoursetbldepartment = tblcoursetbldepartment;
}

public List<tblFaculty> getTblfaculty_tbldepartment() {
	return tblfaculty_tbldepartment;
}

public void setTblfaculty_tbldepartment(List<tblFaculty> tblfaculty_tbldepartment) {
	this.tblfaculty_tbldepartment = tblfaculty_tbldepartment;
}
public tblDepartment() 
{
	super();
	// TODO Auto-generated constructor stub
}

public tblDepartment(int departmentID, String firstName) 
{
	super();
	this.departmentid = departmentID;
	this.firstname = firstName;
}

@Override
public String toString() {
	return "tblDepartment [id=" + id + ", departmentID=" + departmentid + ", firstName=" + firstname + "]";
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getDepartmentid() {
	return departmentid;
}
public void setDepartmentid(int departmentid) {
	this.departmentid = departmentid;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
}
