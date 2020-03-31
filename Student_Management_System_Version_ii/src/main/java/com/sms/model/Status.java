package com.sms.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="status")
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int status;
	private String label;
	
	@OneToMany(mappedBy="students_status")
	private List<tblStudents> students_status;
	
	
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Status(int status, String label) {
		super();
		this.status = status;
		this.label = label;
	}
	
	@Override
	public String toString() {
		return "Status [id=" + id + ", status=" + status + ", label=" + label + ", students_status=" + students_status
				+ "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
