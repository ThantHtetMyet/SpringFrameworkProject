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
@Table(name="tblrejectleave")
public class tblRejectLeave 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startdate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enddate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rejectTime;
	
	@ManyToOne
	private tblFacultyUser tblfacultyuser_tblrejectleave;

	public tblRejectLeave() {
		super();
		// TODO Auto-generated constructor stub
	}

	public tblRejectLeave(int id, Date startdate, Date enddate, tblFacultyUser tblfacultyuser_tblrejectleave) {
		super();
		this.id = id;
		this.startdate = startdate;
		this.enddate = enddate;
		this.tblfacultyuser_tblrejectleave = tblfacultyuser_tblrejectleave;
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

	public tblFacultyUser getTblfacultyuser_tblrejectleave() {
		return tblfacultyuser_tblrejectleave;
	}

	public void setTblfacultyuser_tblrejectleave(tblFacultyUser tblfacultyuser_tblrejectleave) {
		this.tblfacultyuser_tblrejectleave = tblfacultyuser_tblrejectleave;
	}

	public Date getRejectTime() {
		return rejectTime;
	}

	public void setRejectTime(Date rejectTime) {
		this.rejectTime = rejectTime;
	}

	@Override
	public String toString() {
		return "tblRejectLeave [id=" + id + ", startdate=" + startdate + ", enddate=" + enddate + ", rejectTime="
				+ rejectTime + ", tblfacultyuser_tblrejectleave=" + tblfacultyuser_tblrejectleave + "]";
	}
}
