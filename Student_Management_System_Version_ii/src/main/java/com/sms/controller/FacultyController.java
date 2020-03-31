package com.sms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.sms.model.*;
import com.sms.repository.*;
import DAL.*;
import Session.UserSession;

@Controller
@RequestMapping("sms_faculty")
@SessionAttributes("User_Session")
public class FacultyController {

	CRUD_Admin crud_query = new CRUD_Admin();
	CRUD_Users crud_users = new CRUD_Users();
	CRUD_Department crud_department = new CRUD_Department();
	CRUD_Courses crud_courses = new CRUD_Courses();
	CRUD_FacultyUser crud_facultyuser = new CRUD_FacultyUser();
	CRUD_Student crud_student = new CRUD_Student();
	CRUD_StudentGrades crud_grades = new CRUD_StudentGrades();
	CRUD_Leave crud_leave = new CRUD_Leave();
	
	DataService service = new DataService();
	
	tblFacultyUser facultyuser_demo;
	tblCourses course_demo;
	tblDepartment department_demo;
	tblStudentUser studentuser_demo;
	tblApplicantUser applicantuser_demo;
	
	tblStudents student_demo;
	
	@Autowired
	tblFacultyUser_Repository facultyuser_repo;
	
	@Autowired
	tblEnrollCourse_Repository enrollcourse_repo;
	
	@Autowired 
	tblDepartment_Repository department_repo;
	
	@Autowired 
	tblCourses_Repository courses_repo;
	
	@Autowired 
	tblStudentUser_Repository studentuser_repo;
	
	@Autowired
	tblApplicantUser_Repository applicantuser_repo;
	
	@Autowired
	tblAttend_Student_Repository attendstudent_repo;
	
	@Autowired
	tblStudentGrades_Repository studentgrades_repo;
	
	@Autowired
	tblAdminUser_Repository adminuser_repo;

	@Autowired
	tblStudents_Repository student_repo;

	@Autowired
	tblFaculty_Leave_Repository facultyuser_leave_repo;
	
	@Autowired
	tblEnrollLeave_Repository facultyuser_enrollleave_repo;
	
	UserSession user_object;
	
	@RequestMapping("/index")
	public String goFacultyHome()
	{
		return "faculty/index_faculty";
	}
	
	@GetMapping("/index/{id}")
	public String goFacultyHome(HttpSession User_Session,Model model,@PathVariable("id") Integer id)
	{
		System.err.println("----- \t \t Faculty User Index \t \t ------");
		System.out.println(id);
		
		tblFacultyUser facultyuser_obj = crud_facultyuser.getFacultyByFaculty_ID(id);
		
		String departname = facultyuser_obj.getTblfaculty_tblfacultyuser().getFirstname() + " " +facultyuser_obj.getTblfaculty_tblfacultyuser().getMiddlename() + " " + facultyuser_obj.getTblfaculty_tblfacultyuser().getLastname();
		
		// Create Session
		user_object = new UserSession(facultyuser_obj.getUsername(),departname);
		
		model.addAttribute("facultyuser_obj",facultyuser_obj);
		model.addAttribute("department_name",departname);
		
		User_Session.setAttribute("session_key", user_object);
		
		return "faculty/index_faculty";
	}
	
	@GetMapping("/list_of_courses")
	public String Show_List_of_Course(HttpSession User_Session,Model model)
	{
		
		System.err.println("----- \t \t List of Course \t \t ------");
		System.out.println(crud_courses.getCoursesByFacultyUserName(user_object.getName()));
		List<tblCourses> courses_list = crud_courses.getCoursesByFacultyUserName(user_object.getName());
		
		model.addAttribute("courses_list",courses_list);
		return "faculty/show_courses";
	}
	
	// EDIT COURSES
	@GetMapping("/course_edit/{id}")
	public String showEditFacultyUser(Model model,@PathVariable("id") Integer id)
	{
		tblCourses course_sample = courses_repo.findById(id).get();
		System.out.println("----- \t EDIT COURSE \t -----");
		System.out.println(course_sample);
		
		course_demo = course_sample;
		
		model.addAttribute("course_edit_obj",course_sample);
		
		return "faculty/Edit_Course";
	}
	
	// SAVE EDIT COURSES
	@PostMapping("/save_edit_course")
	public String saveFacultyUser(@Valid @ModelAttribute tblCourses edit_course,BindingResult binding_result) 
	{

	System.out.println("--- \t SAVE EDIT \t ---");
		
	if(binding_result.hasErrors())
	{
		System.err.println("--- Binding Err ---");	
		return "faculty/new_facultyuser_Form";
	}
		
	crud_courses.updateCourseBy_courseid(edit_course, course_demo.getId());
		
	//facultyuser_repo.save(new_facultyuser);
	return "redirect:/sms_faculty/list_of_courses";
	}

	// DELETE COURSE
	@GetMapping("/course_delete/{id}")
	public String DeleteCourse(Model model,@PathVariable("id") Integer id)
	{
		tblCourses sample = courses_repo.findById(id).get();
		courses_repo.delete(sample);
		return "redirect:/sms_faculty/list_of_courses";
	}
	
	
	// NEW COURSE
	@GetMapping("/course_new")
	public String NewCourse(Model model)
	{
		System.err.println("----- \t NEW COURSES \t ------");
		tblCourses sample_courses = new tblCourses();
		model.addAttribute("course_new_obj",sample_courses);
		return "faculty/New_Course";
	}
			
	@PostMapping("/save_new_course")
	public String saveNewCourse(@Valid @ModelAttribute tblCourses new_course,BindingResult binding_result) 
	{
		tblDepartment department_obj = crud_department.getDepartment_by_facultyuser_username(user_object.getName());
		tblCourses sample_course = service.createtblCourses(new_course.getCoursecode(), new_course.getCoursename(), new_course.getCourseunit(), department_obj);
		
		courses_repo.save(sample_course);
			
		return "redirect:/sms_faculty/list_of_courses";
	}
	
	// GRADING STUDENT
	//----- LIST OF STUDENTS ---------
	@GetMapping("/list_of_students")
	public String show_student_list(Model model)
	{
		System.err.println("----- \t NEW COURSES \t ------");
		
		List<tblAttend_Student> attend_student_list = attendstudent_repo.findAll();
		List<tblAttend_Student> attendstudent_filter_by_department = new ArrayList<tblAttend_Student>();
		
		tblDepartment current_facultyuser_department_id = crud_department.getDepartment_by_facultyuser_username(user_object.getName());
		
		for(tblAttend_Student temp_attendstudent:attend_student_list)
		{
			if(temp_attendstudent.getAttenddepartmentid() == current_facultyuser_department_id.getId())
			{
				attendstudent_filter_by_department.add(temp_attendstudent);
			}
		}
		
		model.addAttribute("attend_student_list",attendstudent_filter_by_department);
		return "faculty/show_students";
	}
	
	// ------ GRADING ATTENDING STUDENT ---------------
	@GetMapping("/grading_student/{id}")
	public String grading_student(Model model,@PathVariable("id") Integer attend_student_id)
	{
		System.err.println("----- \t Grading Student \t ------");
		
		tblStudents student_object = crud_student.getStudents_by_attendStudent_id(attend_student_id);
		tblCourses course_object = crud_courses.getCourse_by_attendStudent_id(attend_student_id);
		
		student_demo = student_object;
		course_demo = course_object;
		
		tblStudentGrades student_grades_obj = new tblStudentGrades();
		student_grades_obj.setTblstudentgrades_tblcourses(course_object);
		student_grades_obj.setTblstudentgrades_tblstudents(student_object);
		
		model.addAttribute("student_grades_obj",student_grades_obj);
		
		return "faculty/grading_student";
	}
	
	@PostMapping("/save_grading_student")
	public String saveStudentGrades(@Valid @ModelAttribute tblStudentGrades new_grades,BindingResult binding_result) 
	{
		new_grades.setTblstudentgrades_tblcourses(course_demo);
		new_grades.setTblstudentgrades_tblstudents(student_demo);
		
		studentgrades_repo.save(new_grades);
		return "redirect:/sms_faculty/list_of_courses";
	}
	
	// ------ GRADING SCORE CARDS  ---------------
		@GetMapping("/score_cards")
		public String Score_Cards(Model model)
		{
			System.err.println("----- \t Score Cards \t ------");
			tblDepartment department_obj = crud_department.getDepartment_by_facultyuser_username(user_object.getName());
			
			List<tblStudentGrades> student_grades = crud_grades.getAllStudentGrades(department_obj.getId());
			
			
			model.addAttribute("student_grade_lis",student_grades);
			
			return "faculty/score_cards";
		}
		
	// ------------- FACULTY USER LEAVE -------------------
		@GetMapping("/apply_leave")
		public String Faculty_Leave(Model model)
		{	
			System.err.println("----- \t APPLY LEAVE \t ------");
			int facultyuser_id = crud_facultyuser.getFacultyID_by_username(user_object.getName());
			
			tblFacultyUser sample_faculty_user = facultyuser_repo.findById(facultyuser_id).get();
			
			tblEnrollLeave faculty_leave = new tblEnrollLeave();
			faculty_leave.setTblfacultyuser_tblfacultyenrollleave(sample_faculty_user);
			model.addAttribute("enroll_leave_obj",faculty_leave);
			
			int faculty_user_id = crud_facultyuser.getFacultyUserID_by_username(user_object.getName());
			List<tblFaculty_Leave> faculty_leave_list = crud_leave.getLeave_by_facultyuser_id(faculty_user_id);
			System.out.println("*****");
			System.out.println(faculty_leave_list);
			
			model.addAttribute("applied_leave_obj",faculty_leave_list);
			
			return "faculty/apply_leave";
		}
		
		@PostMapping("/save_enroll_leave")
		public String saveEnrollLeave(@Valid @ModelAttribute tblEnrollLeave new_Leave,BindingResult binding_result) 
		{
			tblEnrollLeave facultyuser_Leave = new_Leave;
			int facultyuser_id = crud_facultyuser.getFacultyUserID_by_username(user_object.getName());
			tblFacultyUser sample_faculty_user = facultyuser_repo.findById(facultyuser_id).get();
			sample_faculty_user.setId(facultyuser_id);
			
			facultyuser_Leave.setTblfacultyuser_tblfacultyenrollleave(sample_faculty_user);
			
			System.out.println(facultyuser_Leave);
			
			facultyuser_enrollleave_repo.save(facultyuser_Leave);
			
			return "redirect:/sms_faculty/apply_leave";
		}
}
