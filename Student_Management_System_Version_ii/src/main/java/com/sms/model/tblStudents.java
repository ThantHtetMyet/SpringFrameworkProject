package com.sms.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tblstudents")
public class tblStudents {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String studentid;
	private String firstName;
	private String lastName;
	private String middelName;
	private String gender;
	private String birthDate;
	private String degree;
	private String address;
	private String mobile;
	private String email;

	@OneToMany(mappedBy="tblstudentgrades_tblstudents")
	private List<tblStudentGrades> tblstudentgrades_tblstudents;
	
	@ManyToOne
	private level students_level;
	
	@ManyToOne
	private semester students_semester;

	@ManyToOne
	private Status students_status;
	
	@OneToOne
	private tblGPA students_tlbgpa;
	
	public tblGPA getStudents_tlbgpa() {
		return students_tlbgpa;
	}

	public tblStudents(String studentID) {
		super();
		this.studentid = studentID;
	}
	public String getStudentid() {
		return studentid;
	}

	public void setStudentid(String studentID) {
		this.studentid = studentID;
	}

	public tblStudents(String studentID, String firstName, String lastName, String middelName, String gender,String birthDate, String degree, String address, String mobile, String email) 
	{
		super();
		this.studentid = studentID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middelName = middelName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.degree = degree;
		this.address = address;
		this.mobile = mobile;
		this.email = email;
	}

	public List<tblStudentGrades> getTblstudentgrades_tblstudents() {
		return tblstudentgrades_tblstudents;
	}

	public void setTblstudentgrades_tblstudents(List<tblStudentGrades> tblstudentgrades_tblstudents) {
		this.tblstudentgrades_tblstudents = tblstudentgrades_tblstudents;
	}

	public void setStudents_tlbgpa(tblGPA students_tlbgpa) {
		this.students_tlbgpa = students_tlbgpa;
	}
	public tblStudents() 
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	public tblGPA getTblgpa() {
		return students_tlbgpa;
	}
	public void setTblgpa(tblGPA tblgpa) {
		this.students_tlbgpa = tblgpa;
	}
	
	public tblStudents(String firstName, String lastName, String middelName, String gender, String birthDate,
			String degree, String address, String mobile, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.middelName = middelName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.degree = degree;
		this.address = address;
		this.mobile = mobile;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "tblStudents [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", middelName="
				+ middelName + ", gender=" + gender + ", birthDate=" + birthDate + ", degree=" + degree + ", address="
				+ address + ", mobile=" + mobile + ", email=" + email + ", students_level=" + students_level
				+ ", students_semester=" + students_semester + ", tblgpa=" + students_tlbgpa + ", students_status="
				+ students_status + ", tblstudentgrades_tblstudents=" + tblstudentgrades_tblstudents + "]";
	}
	
	
	public level getStudents_level() {
		return students_level;
	}
	public void setStudents__level(level students_level) {
		this.students_level = students_level;
	}
	
	public semester getStudents_semester() {
		return students_semester;
	}
	public void setStudents_semester(semester students_semester) {
		this.students_semester = students_semester;
	}
	public void setStudents_level(level students_level) {
		this.students_level = students_level;
	}
	
	
	public Status getStudents_status() {
		return students_status;
	}
	public void setStudents_status(Status students_status) {
		this.students_status = students_status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddelName() {
		return middelName;
	}
	public void setMiddelName(String middelName) {
		this.middelName = middelName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
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
