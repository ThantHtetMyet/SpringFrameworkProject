package com.sms.custom;

public class custom_tblcourses 
{
	private String coursecode;
	private String coursename;
	private String courseunit;
	
	public custom_tblcourses(String coursecode, String coursename, String courseunit) {
		super();
		this.coursecode = coursecode;
		this.coursename = coursename;
		this.courseunit = courseunit;
	}
	public custom_tblcourses() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "custom_tblcourses [coursecode=" + coursecode + ", coursename=" + coursename + ", courseunit="
				+ courseunit + "]";
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
