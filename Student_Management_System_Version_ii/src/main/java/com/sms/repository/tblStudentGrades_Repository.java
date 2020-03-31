package com.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sms.model.*;

@Repository
public interface tblStudentGrades_Repository extends JpaRepository<tblStudentGrades,Integer>
{
	@Query("SELECT a FROM tblStudentGrades a WHERE tblstudentgrades_tblstudents_id = ?1")
    List<tblStudentGrades> findByStudentID(int student_id);
    
 }
