package com.sms.controller;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.sms.model.*;
import com.sms.repository.*;
import DAL.*;
import DAL.DataService;
import Session.*;

@Controller
@SessionAttributes("User_Session")
@RequestMapping("sms_student")
public class StudentController {

	CRUD_Admin crud_query = new CRUD_Admin();
	CRUD_Student crud_student = new CRUD_Student();
	CRUD_Courses crud_courses = new CRUD_Courses();
	CRUD_Enrollcourses crud_enroll_courses = new CRUD_Enrollcourses();
	CRUD_MainStudent crud_main_student = new CRUD_MainStudent();
	CRUD_StudentGrades crud_studentgrades = new CRUD_StudentGrades();
	
	DataService service = new DataService();
	
	tblFacultyUser facultyuser_demo;
	tblCourses course_demo;
	tblDepartment department_demo;
	tblStudentUser studentuser_demo;
	tblApplicantUser applicantuser_demo;
	
	@Autowired
	tblFacultyUser_Repository facultyuser_repo;
	
	@Autowired
	tblEnrollCourse_Repository enrollcourse_repo;
	
	@Autowired 
	tblDepartment_Repository department_repo;
	
	@Autowired 
	tblCourses_Repository course_repository;
	
	@Autowired 
	tblStudentUser_Repository studentuser_repo;
	
	@Autowired 
	tblStudents_Repository mainstudent_repo;
	
	@Autowired
	tblApplicantUser_Repository applicantuser_repo;
	
	@Autowired
	tblStudentGrades_Repository studentgrades_repo;
	
	UserSession user_object;
	
	@GetMapping("/index/{id}")
	public String goStudentHome(HttpSession User_Session,Model model,@PathVariable("id") Integer id)
	{
		System.err.println("----- \t \t Student User Index \t \t ------");
		System.out.println(id);
		
		tblStudentUser studentuser_obj = studentuser_repo.findById(id).get();
		
		// Create Session
		user_object = new UserSession(studentuser_obj.getUsername(),"Student");
		
		model.addAttribute("student_obj",studentuser_obj);
		
		User_Session.setAttribute("session_key", user_object);
		
		return "student/index_student";
	}
	
	@RequestMapping("/show_enroll_courses")
	public String Student_Show_Enroll_Courses(Model model)
	{
		System.err.println("------ \t ENROLL COURSES  \t -----");
		
		List<tblEnrollCourse> temp_enroll_course = crud_enroll_courses.getEnrollCourseByUserName(user_object.getName());
		
		//List<tblFacultyUser> facultyuser_lis = facultyuser_repo.findAll();
		
		model.addAttribute("enrollcourse_list",temp_enroll_course);
		
		return "student/show_enroll_courses";
	}	
	
	/* @GetMapping("/show_available_courses") */
	
	@RequestMapping(value = "/show_available_courses", method = RequestMethod.GET)
	public String Student_Show_Available_Courses(Model model,@RequestParam("page") Optional<Integer> page,@RequestParam("size") Optional<Integer> size)
	{
		CourseService course_service = new CourseService();
		
		System.err.println("------ \t Available Courses  \t -----");
		
		List<tblCourses> course_list = course_repository.findAll();
		
		List<tblEnrollCourse> enroll_list = enrollcourse_repo.findAll();
		
		List<tblCourses> temp_courses = crud_enroll_courses.CompareCourse(course_list,enroll_list);
		
		int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
 
        Page<tblCourses> bookPage = course_service.findPaginated(PageRequest.of(currentPage - 1, pageSize),temp_courses);
        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        
		model.addAttribute("course_list",bookPage);
		
		return "student/show_available_courses";
	}
	
	@GetMapping("/enroll_course/{name}")
	public String Student_Enroll_Courses(Model model,@PathVariable("name") String course_name)
	{
		int department_id = crud_courses.getDepartmentID_by_coursename(course_name);
		
		enrollcourse_repo.save(new tblEnrollCourse(department_id,course_name,user_object.getName()));
		
		return "redirect:/sms_student/show_available_courses";
	}
	
	@GetMapping("/enrollcourse_cancel/{id}")
	public String Student_Enroll_Courses(Model model,@PathVariable("id") Integer course_id)
	{
		System.out.println("--- \t Enroll Course Cancel \t ---");
		
		enrollcourse_repo.deleteById(course_id);
		//deleteEnrollCourseBycourseID_userName(course_id,user_obj);
		 
		return "redirect:/sms_student/show_enroll_courses";
	}
	
	
	@GetMapping("/show_gpa")
	public String Student_Show_GPA(Model model)
	{
		System.out.println("--- \t Show GPA \t ---");
		double student_gpa = 0.0;
		double unit_grade = 0.0;
		double total_unit = 0.0;
		List<tblStudentGrades> student_grades_lis = crud_studentgrades.getStudentGrades_Courses(user_object.getName());
		for(tblStudentGrades temp_tblStudentGrades : student_grades_lis)
		{
			tblCourses temp_course = temp_tblStudentGrades.getTblstudentgrades_tblcourses();
			total_unit = total_unit + Integer.parseInt(temp_course.getCourseunit());
		}
		
		for(tblStudentGrades temp_tblStudentGrades : student_grades_lis)
		{
			tblCourses temp_course = temp_tblStudentGrades.getTblstudentgrades_tblcourses();
			
			String grade = temp_tblStudentGrades.getGrade();
			int course_unit = Integer.parseInt(temp_course.getCourseunit());
			
			switch(grade)
			{
			case "A" : 
				unit_grade = unit_grade + (5 * course_unit);
				break;
			case "B":
				unit_grade = unit_grade + (3.5 * course_unit); 
				break;
			case "C":
				unit_grade = unit_grade + (2 * course_unit); 
				break;
			}
			
		}
		student_gpa = unit_grade / total_unit;
		model.addAttribute("student_gpa",student_gpa);
		model.addAttribute("student_grades_lis",student_grades_lis);
		
		return "student/show_gpa";
	}
	

	@RequestMapping("/show_copy_of_grades")
	public String Student_Show_copy_of_grades(Model model)
	{
		System.err.println("------ \t COPY of GRADES  \t -----");
		//List<tblFacultyUser> facultyuser_lis = facultyuser_repo.findAll();
		
		List<tblStudentGrades> student_grades_lis = crud_studentgrades.getStudentGrades_Courses(user_object.getName());
		
		model.addAttribute("student_grades_lis",student_grades_lis);
		
		//List<tblStudentGrades> student_grades = studentgrades_repo.findB
		return "student/copy_of_grades";
	}
	
}
