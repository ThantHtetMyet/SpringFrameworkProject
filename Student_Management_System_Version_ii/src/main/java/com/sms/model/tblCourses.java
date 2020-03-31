package com.sms.model;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tblcourses")
public class tblCourses 
{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String coursecode;
private String coursename;
private String courseunit;

@OneToMany(mappedBy="tblstudentgrades_tblcourses")
private List<tblStudentGrades> tblstudentgrades_tblcourses;

@ManyToOne
private tblDepartment tblcoursetbldepartment;

@Transient
private int transient_course_department;

public int getTransient_course_department() {
	return transient_course_department;
}
public void setTransient_course_department(int transient_course_department) {
	this.transient_course_department = transient_course_department;
}

public tblCourses() 
{
	super();
	// TODO Auto-generated constructor stub
}
public tblCourses(String coursecode, String coursename, String courseunit) {
	super();
	this.coursecode = coursecode;
	this.coursename = coursename;
	this.courseunit = courseunit;
}
public List<tblStudentGrades> getTblstudentgrades_tblcourses() {
	return tblstudentgrades_tblcourses;
}
public void setTblstudentgrades_tblcourses(List<tblStudentGrades> tblstudentgrades_tblcourses) {
	this.tblstudentgrades_tblcourses = tblstudentgrades_tblcourses;
}

@Override
public String toString() {
	return "tblCourses [id=" + id + ", coursecode=" + coursecode + ", coursename=" + coursename + ", courseunit="
			+ courseunit + ", tblstudentgrades_tblcourses=" + tblstudentgrades_tblcourses + ", tblcoursetbldepartment="
			+ tblcoursetbldepartment + ", transient_course_department=" + transient_course_department + "]";
}


public tblDepartment getTblcoursetbldepartment() {
	return tblcoursetbldepartment;
}
public void setTblcoursetbldepartment(tblDepartment tblcoursetbldepartment) {
	this.tblcoursetbldepartment = tblcoursetbldepartment;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCoursecode() {
	return coursecode;
}
public void setCoursecode(String coursecode) {
	this.coursecode = coursecode;
}
public String getCoursename() {
	return coursename;
}
public void setCoursename(String coursename) {
	this.coursename = coursename;
}
public String getCourseunit() {
	return courseunit;
}
public void setCourseunit(String courseunit) {
	this.courseunit = courseunit;
}
}
