package com.sms.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tblenrollleave")
public class tblEnrollLeave {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enddate;
	
	@ManyToOne
	private tblFacultyUser tblfacultyuser_tblfacultyenrollleave;
	
	public tblFacultyUser getTblfacultyuser_tblfacultyenrollleave() {
		return tblfacultyuser_tblfacultyenrollleave;
	}
	public void setTblfacultyuser_tblfacultyenrollleave(tblFacultyUser tblfacultyuser_tblfacultyenrollleave) {
		this.tblfacultyuser_tblfacultyenrollleave = tblfacultyuser_tblfacultyenrollleave;
	}
	public tblEnrollLeave() {
		super();
		// TODO Auto-generated constructor stub
	}
	public tblEnrollLeave(int id, Date startdate, Date enddate) {
		super();
		this.id = id;
		this.startdate = startdate;
		this.enddate = enddate;
	}
	@Override
	public String toString() {
		return "tblEnrollLeave [id=" + id + ", startdate=" + startdate + ", enddate=" + enddate
				+ ", tblfacultyuser_tblfacultyenrollleave=" + tblfacultyuser_tblfacultyenrollleave + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	
}
