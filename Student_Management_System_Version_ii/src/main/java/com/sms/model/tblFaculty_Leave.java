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
@Table(name="tblfacultyleave")
public class tblFaculty_Leave {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enddate;
	
	@ManyToOne
	private tblFacultyUser tblfacultyuser_tblfacultyleave;

	public tblFaculty_Leave() {
		super();
		// TODO Auto-generated constructor stub
	}

	public tblFaculty_Leave(Date startdate, Date enddate)
	{
		super();
		this.startdate = startdate;
		this.enddate = enddate;
	}

	@Override
	public String toString() {
		return "tblFaculty_Leave [id=" + id + ", startdate=" + startdate + ", enddate=" + enddate
				+ ", tblfacultyuser_tblfacultyleave=" + tblfacultyuser_tblfacultyleave + "]";
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

	public tblFacultyUser getTblfacultyuser_tblfacultyleave() {
		return tblfacultyuser_tblfacultyleave;
	}

	public void setTblfacultyuser_tblfacultyleave(tblFacultyUser tblfacultyuser_tblfacultyleave) {
		this.tblfacultyuser_tblfacultyleave = tblfacultyuser_tblfacultyleave;
	}
}
