package com.sms.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.sms.model.*;
import Ajax.AjaxResponseBodyStudent;
import DAL.*;
import Ajax.*;
import com.sms.repository.*;

@RestController
public class RESTCONTROLLER {
	CRUD_Student crud_student = new CRUD_Student();
	CRUD_Admin crud_admin = new CRUD_Admin();
	CRUD_Faculty crud_faculty = new CRUD_Faculty();
	
	@Autowired
	tblEnrollCourse_Repository enrollcourse_repo;
	
	@Autowired
	tblAttend_Student_Repository attendstudent_repo;
	
	@Autowired
	tblStudentGrades_Repository studentgrades_repo;
	
	// STUDENT LOGIN
	@PostMapping("/student_api/search")
    public ResponseEntity<?> getStudentResultViaAjax(@Valid @RequestBody tblStudentUser search, Errors errors) 
	{
		
		System.err.println("----- \t STUDENT AJAX WORK \t -----");
		
		AjaxResponseBodyStudent result = new AjaxResponseBodyStudent();
		
        result  = crud_student.getStudentById(search.getUsername(), search.getPassword());
	
        return ResponseEntity.ok(result);
    }
	
	// ADMIN USER LOGIN
	@PostMapping("/admin_api/search")
    public ResponseEntity<?> getAdminResultViaAjax(@Valid @RequestBody tblAdminUser search, Errors errors) 
	{
			System.err.println("----- \t ADMIN AJAX WORK \t -----");
			
			AjaxResponseBodyAdmin result = new AjaxResponseBodyAdmin();
	        
	        result  = crud_admin.getAdminUser(search.getUsername(), search.getPassword());
			
	        System.out.println("Sample User");
	        System.out.println(result.getMsg());
	        System.out.println(result.getResult());
	        
	        return ResponseEntity.ok(result);

	}
	
	// ADMIN USER LOGIN
	@PostMapping("/faculty_api/search")
	public ResponseEntity<?> getFacultyResultViaAjax(@Valid @RequestBody tblFacultyUser search, Errors errors) 
	{
		System.err.println("----- \t FACULTY AJAX WORK \t -----");
		
		AjaxResponseBodyFaculty result = new AjaxResponseBodyFaculty();
		        
		result  = crud_faculty.getFacultyUser(search.getUsername(), search.getPassword());
				
		System.out.println("----- \t Faculty User \t ------");
		System.out.println(result.getMsg());
		System.out.println(result.getResult());
		
		return ResponseEntity.ok(result);

	}
	
	// ADMIN USER LOGIN
		@PostMapping("/admin_api/approve_enroll_course")
		public ResponseEntity<?> getEnrollResultViaAjax(@Valid @RequestBody tblAttend_Student search, Errors errors) 
		{
			System.err.println("----- \t Enroll User AJAX WORK \t -----");
			System.out.println(search);
			
			String[] coursename_and_id = search.getAttendcoursename().split("\\*");
			String approveusername = search.getAttendusername();
			int departmentid = search.getAttenddepartmentid();
			
			enrollcourse_repo.deleteById(Integer.parseInt(coursename_and_id[1]));
			
			attendstudent_repo.save(new tblAttend_Student(coursename_and_id[0],approveusername,departmentid));
			
			AjaxResponseBodyApproveEnrollCourse result = new AjaxResponseBodyApproveEnrollCourse();
			
			result.setMsg("match");
			
			return ResponseEntity.ok(result);
			
		}
}
