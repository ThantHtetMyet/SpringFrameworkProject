package com.sms.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="level")
public class level {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int level;
	private String label;
	
	@OneToMany(mappedBy="students_level")
	private List<tblStudents> students_level;
	
	public level() {
		super();
		// TODO Auto-generated constructor stub
	}
	public level(int level, String label) {
		super();
		this.level = level;
		this.label = label;
	}
	
	@Override
	public String toString() {
		return "level [id=" + id + ", level=" + level + ", label=" + label + ", students_level=" + students_level + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
}
