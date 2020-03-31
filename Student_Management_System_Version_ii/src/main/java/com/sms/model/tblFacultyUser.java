package com.sms.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tblfacultyuser")

public class tblFacultyUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	
	@ManyToOne
	private tblFaculty tblfaculty_tblfacultyuser;


	@OneToMany(mappedBy="tblfacultyuser_tblfacultyleave",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<tblFacultyUser> tblfacultyuser_tblfacultyleave;
	
	@OneToMany(mappedBy="tblfacultyuser_tblfacultyenrollleave",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<tblFacultyUser> tblfacultyuser_tblfacultyenrollleave;
	
	@OneToMany(mappedBy="tblfacultyuser_tblrejectleave",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<tblFacultyUser> tblfacultyuser_tblrejectleave;
	
	@Transient
	private int transient_facultyuser_faculty;

	
	public int getTransient_facultyuser_faculty() {
		return transient_facultyuser_faculty;
	}
	public void setTransient_facultyuser_faculty(int transient_facultyuser_faculty) {
		this.transient_facultyuser_faculty = transient_facultyuser_faculty;
	}
	public tblFacultyUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public tblFaculty getTblfaculty_tblfacultyuser() {
		return tblfaculty_tblfacultyuser;
	}
	public void setTblfaculty_tblfacultyuser(tblFaculty tblfaculty_tblfacultyuser) {
		this.tblfaculty_tblfacultyuser = tblfaculty_tblfacultyuser;
	}
	public tblFacultyUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	@Override
	public String toString() {
		return "tblFacultyUser [" + ", username=" + username + ", password=" + password + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
