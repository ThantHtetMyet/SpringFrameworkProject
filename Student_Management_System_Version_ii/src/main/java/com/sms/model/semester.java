package com.sms.model;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="semester")
public class semester 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy="students_semester")
	private List<tblStudents> students_semester;
	
	private int semester;
	
	private String label;
	
	public semester() 
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public semester( int semester, String label) 
	{
		super();
		this.semester = semester;
		this.label = label;
	}
	
	@Override
	public String toString() {
		return "semester [id=" + id + ", students_semester=" + students_semester + ", semester=" + semester + ", label="
				+ label + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
