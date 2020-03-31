package com.sms.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tblstudentgrades")
public class tblStudentGrades 
{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String grade;
private int semester;
private int level;
private int facultytypeid;

@ManyToOne
private tblStudents tblstudentgrades_tblstudents;

@ManyToOne
private tblCourses tblstudentgrades_tblcourses;


public tblStudents getTblstudentgrades_tblstudents() {
	return tblstudentgrades_tblstudents;
}
public void setTblstudentgrades_tblstudents(tblStudents tblstudentgrades_tblstudents) {
	this.tblstudentgrades_tblstudents = tblstudentgrades_tblstudents;
}


public tblCourses getTblstudentgrades_tblcourses() {
	return tblstudentgrades_tblcourses;
}
public void setTblstudentgrades_tblcourses(tblCourses tblstudentgrades_tblcourses) {
	this.tblstudentgrades_tblcourses = tblstudentgrades_tblcourses;
}
public tblStudentGrades() {
	super();
	// TODO Auto-generated constructor stub
}
public tblStudentGrades(String grade, int semester, int level, int facultytypeid) 
{
	super();
	this.grade = grade;
	this.semester = semester;
	this.level = level;
	this.facultytypeid = facultytypeid;
}
@Override
public String toString() {
	return "tblStudentGrades [id=" + id + ", grade=" + grade + ", semester=" + semester + ", level=" + level
			+ ", facultytypeid=" + facultytypeid + ", tblstudentgrades_tblstudents=" + tblstudentgrades_tblstudents
			+ ", tblstudentgrades_tblcourses=" + tblstudentgrades_tblcourses + "]";
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getGrade() {
	return grade;
}
public void setGrade(String grade) {
	this.grade = grade;
}
public int getSemester() {
	return semester;
}
public void setSemester(int semester) {
	this.semester = semester;
}
public int getLevel() {
	return level;
}
public void setLevel(int level) {
	this.level = level;
}
public int getFacultytypeid() {
	return facultytypeid;
}
public void setFacultytypeid(int facultytypeid) {
	this.facultytypeid = facultytypeid;
}


}
