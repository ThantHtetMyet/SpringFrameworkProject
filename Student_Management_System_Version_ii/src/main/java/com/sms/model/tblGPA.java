package com.sms.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tblgpa")
public class tblGPA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String gpaCalculated;
	
	@OneToOne(mappedBy="students_tlbgpa")
	private tblStudents students_tlbgpa;
	
	
	public tblGPA() {
		super();
		// TODO Auto-generated constructor stub
	}
	public tblGPA(String gpaCalculated) {
		super();
		this.gpaCalculated = gpaCalculated;
	}
	@Override
	public String toString() {
		return "tblGPA [id=" + id + ", gpaCalculated=" + gpaCalculated + ", students_tlbgpa=" + students_tlbgpa + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGpaCalculated() {
		return gpaCalculated;
	}
	public void setGpaCalculated(String gpaCalculated) {
		this.gpaCalculated = gpaCalculated;
	}
	
	
}
