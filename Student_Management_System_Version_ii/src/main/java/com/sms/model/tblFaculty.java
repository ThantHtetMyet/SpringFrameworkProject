package com.sms.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tblfaculty")
public class tblFaculty 
{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String firstname;
private String lastname;
private String middlename;

@ManyToOne
private tblDepartment tblfaculty_tbldepartment;

@OneToMany(mappedBy="tblfaculty_tblfacultyuser")
private List<tblFacultyUser> tblfaculty_tblfacultyuser;

public tblFaculty() {
	super();
	// TODO Auto-generated constructor stub
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

public String getMiddlename() {
	return middlename;
}

public void setMiddlename(String middlename) {
	this.middlename = middlename;
}

public tblDepartment getTblfaculty_tbldepartment() {
	return tblfaculty_tbldepartment;
}

public void setTblfaculty_tbldepartment(tblDepartment tblfaculty_tbldepartment) {
	this.tblfaculty_tbldepartment = tblfaculty_tbldepartment;
}

public tblFaculty(String firstname, String lastname, String middlename) {
	super();
	this.firstname = firstname;
	this.lastname = lastname;
	this.middlename = middlename;
}

@Override
public String toString() {
	return "tblFaculty [" +", firstname=" + firstname + ", lastname=" + lastname + ", middlename=" + middlename
			+ "]";
}
}
