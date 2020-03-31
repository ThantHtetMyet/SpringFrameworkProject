package com.sms.controller;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.sms.model.*;
import com.sms.repository.*;
import DAL.*;
import Session.UserSession;

@Controller
@RequestMapping("sms_admin")
@SessionAttributes("User_Session")
public class AdminController {
	
	CRUD_Admin crud_query = new CRUD_Admin();
	CRUD_Users crud_users = new CRUD_Users();
	CRUD_Department crud_department = new CRUD_Department();
	CRUD_StudentGrades crud_studentgrades = new CRUD_StudentGrades();
	CRUD_Courses crud_courses = new CRUD_Courses();
	CourseService course_service = new CourseService();
	
	DataService service = new DataService();
	
	tblFacultyUser facultyuser_demo;
	tblCourses course_demo;
	tblDepartment department_demo;
	tblStudentUser studentuser_demo;
	tblApplicantUser applicantuser_demo;
	
	@Autowired
	tblFacultyUser_Repository facultyuser_repo;
	
	@Autowired
	tblFaculty_Repository faculty_repo;
	
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
	tblAdminUser_Repository adminuser_repo;
	
	@Autowired
	tblFaculty_Leave_Repository facultyuser_leave_repo;
	
	@Autowired
	tblEnrollLeave_Repository facultyuser_enrollleave_repo;
	
	UserSession user_object;
	
	@GetMapping("/index")
	public String goAdminHome()
	{		
		return "admin/index_admin";
	}
	
	@GetMapping("/index/{id}")
	public String goAdminHome(HttpSession User_Session,Model model,@PathVariable("id") Integer id)
	{
		System.err.println("----- \t \t Admin User Index \t \t ------");
		System.out.println(id);
		
		tblAdminUser adminuser_obj = adminuser_repo.findById(id).get();
		// Create Session
		user_object = new UserSession(adminuser_obj.getUsername(),"Admin");
		model.addAttribute("adminuser_obj",adminuser_obj);
		User_Session.setAttribute("session_key", user_object);
		
		return "admin/index_admin";
	}
	
	// ----------------------------------------- START FACULTY -------------------------------------------------
	@RequestMapping("/show_faculty_users")
	public String Admin_Show_FacultyUser(Model model)
	{
		List<tblFacultyUser> facultyuser_lis = facultyuser_repo.findAll();
		
		model.addAttribute("facultyuser_lis",facultyuser_lis);
		
		return "admin/faculty/ShowFacultyUsers";
	}
	
	// EDIT FACULTY USERS
	@GetMapping("/faculty_user_edit/{id}")
	public String showEditFacultyUser(Model model,@PathVariable("id") Integer id)
	{
		tblFacultyUser faculty_user_sample = facultyuser_repo.findById(id).get();
		
		facultyuser_demo = faculty_user_sample;
		
		model.addAttribute("faculty_edit_obj",faculty_user_sample);
		
		return "admin/faculty/Edit_FacultyUser";
	}
	
	// EDIT FACULTY USERS
	@PostMapping("/save_edit_facultyuser")
	public String saveFacultyUser(@Valid @ModelAttribute tblFacultyUser edit_facultyuser,BindingResult binding_result) 
	{

	System.out.println("--- \t SAVE EDIT \t ---");
	
	if(binding_result.hasErrors())
	{
		System.err.println("--- Binding Err ---");	
		return "faculty/new_facultyuser_Form";
	}
	
	crud_users.updateFacultyUser(edit_facultyuser,facultyuser_demo.getId());
	
	//facultyuser_repo.save(new_facultyuser);
	return "redirect:/sms_admin/show_faculty_users";
}

	// DELETE FACULTY
	@GetMapping("/faculty_user_delete/{id}")
	public String DeleteFacultyUser(Model model,@PathVariable("id") Integer id)
	{
		tblFacultyUser sample = facultyuser_repo.findById(id).get();
		facultyuser_repo.delete(sample);
		return "redirect:/sms_admin/show_faculty_users";
	}
	
	// NEW FACULTY
	@GetMapping("/faculty_user_new")
	public String NewFacultyUser(Model model)
	{
		System.err.println("----- \t NEW FACULTY USER \t ------");
		
		List<tblFacultyUser> faculty_user_list = facultyuser_repo.findAll();
		
		List<tblFaculty> faculty_list = faculty_repo.findAll();
		
		int max_id = 0;
		for(tblFacultyUser temp: faculty_user_list)
		{
			if(max_id<temp.getId())
				{
				max_id = temp.getId();
				}
		}
			
		
		tblFacultyUser demo = new tblFacultyUser("hello","hello");
		
		model.addAttribute("faculty_list",faculty_list);
		model.addAttribute("faculty_new_obj",demo);
		
		return "admin/faculty/New_FacultyUser";
		
	}
	
	@PostMapping("/save_new_faculty_user")
	public String saveNewFacutlyUser(@Valid @ModelAttribute tblFacultyUser new_facultyuser,BindingResult binding_result) 
	{
		tblFaculty department_obj = crud_query.getFaculty(new_facultyuser.getTransient_facultyuser_faculty());
		
		tblFacultyUser sample_facultyuser = service.createFacultyUser(new_facultyuser.getUsername(),new_facultyuser.getPassword(),department_obj);
		
		facultyuser_repo.save(sample_facultyuser);
		
		return "redirect:/sms_admin/show_faculty_users";
	}
	//----------------------------- END FACULTY ---------------------------------------------------------------------------------
	

	// ----------------------------------------- START COURSES -------------------------------------------------
	@RequestMapping(value = "/show_courses", method = RequestMethod.GET)
	public String Admin_Show_Courses(Model model)
	{	
		System.err.println("------ \t Show Admin Courses \t ------");
        List<tblCourses> courses_list = crud_courses.getCourses_Department();
        
        model.addAttribute("courses_list", courses_list);
 
		return "/admin/courses/ShowCourses";
	}
	
	// NEW COURSE
	@GetMapping("/course_new")
	public String NewCourse(Model model)
	{
		System.err.println("----- \t NEW COURSES \t ------");
		
		List<tblDepartment> department_list = department_repo.findAll();
		
		tblCourses sample_courses = new tblCourses();
		
		model.addAttribute("course_new_obj",sample_courses);
		model.addAttribute("department_lis",department_list);
		
		return "admin/courses/New_Course";
			
	}
		
	@PostMapping("/save_new_course")
	public String saveNewCourse(@Valid @ModelAttribute tblCourses new_course,BindingResult binding_result) 
	{
		System.err.println("--- Save New Course ----");
		
		tblDepartment department_obj = crud_query.getDepartment(new_course.getTransient_course_department());
				
		tblCourses sample_course = service.createtblCourses(new_course.getCoursecode(), new_course.getCoursename(), new_course.getCourseunit(), department_obj);
		
		courses_repo.save(sample_course);
		
		return "redirect:/sms_admin/show_courses";
	}
	
	// Edit COURSE
	@GetMapping("/course_edit/{id}")
	public String showEditCourse(Model model,@PathVariable("id") Integer id)
	{
		System.err.println("------ \t COURSE EDITING \t ------");
		System.out.println("ID is:\t " + id);
		
		tblCourses edit_course_obj = crud_courses.getOneCourse_Department(id);
		
		tblCourses course_sample = courses_repo.findById(id).get();
		
		List<tblDepartment> department_lis = department_repo.findAll();
		
		course_demo = course_sample;
		
		model.addAttribute("course_edit_obj",edit_course_obj);
		model.addAttribute("department_lis",department_lis);
		
		return "admin/courses/Edit_Course";
	}
		
	// Edit COURSES
	@PostMapping("/save_edit_course")
	public String saveCourse(@Valid @ModelAttribute tblCourses edit_course_obj,BindingResult binding_result) 
	{
		System.out.println("--- \t SAVE EDIT \t ---");
		
		if(binding_result.hasErrors())
		{
			System.err.println("--- Binding Err ---");	
			return "faculty/new_facultyuser_Form";
		}
		
		//tblDepartment department_obj = crud_query.getDepartment(edit_course_obj.getTransient_course_department());
		
		System.out.println("New EDIT OBJECT " + edit_course_obj);
		System.out.println("EDIT OBJECT ID: " + course_demo.getId());
		
		tblCourses sample = courses_repo.findById(course_demo.getId()).get();
		
		courses_repo.delete(sample);
		
		tblCourses new_course = crud_courses.getUpdateCourse(course_demo,edit_course_obj);
		
		courses_repo.save(new_course);
		
		return "redirect:/sms_admin/show_courses";
	}
	
	// DELETE FACULTY
	@GetMapping("/course_delete/{id}")
	public String DeleteCourse(Model model,@PathVariable("id") Integer id)
	{
		System.err.println("----- COURSE DELETE -----");
		System.out.println(id);
		
		tblCourses sample = courses_repo.findById(id).get();
		courses_repo.delete(sample);
		return "redirect:/sms_admin/show_courses";
	}
		
	
	// ----------------------------------------- START DEPARTMENTS -------------------------------------------------
	@GetMapping("/show_departments")
	public String Admin_Show_Department(Model model)
	{	
		List<tblDepartment> department_list = department_repo.findAll();
			
		model.addAttribute("department_list",department_list);
			
		System.err.println("------ \t Show Admin Courses \t ------");
		return "/admin/department/ShowDepartments";
	}
	
	// NEW COURSE
	@GetMapping("/department_new")
	public String NewDepartment(Model model)
	{
		System.err.println("----- \t NEW DEPARTMENT \t ------");
				
		model.addAttribute("department_new_obj",new tblDepartment());
				
		return "admin/department/New_Department";
				
	}
	
	@PostMapping("/save_new_department")
	public String saveNewDeparment(@Valid @ModelAttribute tblDepartment new_department,BindingResult binding_result) 
	{
		System.err.println("----- \t SAVE NEW DEPARTMENT \t -----");
		
		department_repo.save(new_department);
		return "redirect:/sms_admin/show_departments";
	}
	
	// UPDATE COURSE
	@GetMapping("/department_edit/{id}")
	public String showEditDepartment(Model model,@PathVariable("id") Integer id)
	{
		tblDepartment department_sample = department_repo.findById(id).get();
		department_demo = department_sample;
		
		model.addAttribute("department_edit_obj",department_sample);
		
		return "admin/department/Edit_Department";
	}
			
	// UPDATE DEPARTMENTS
	@PostMapping("/save_edit_department")
	public String saveDepartment(@Valid @ModelAttribute tblDepartment edit_department_obj,BindingResult binding_result) 
	{
		System.out.println("--- \t SAVE EDIT \t ---");
			
		if(binding_result.hasErrors())
		{
			System.err.println("--- Binding Err ---");	
			return "faculty/new_facultyuser_Form";
		}
		
		crud_department.updateDepartment(edit_department_obj, department_demo.getId());
		
		//facultyuser_repo.save(new_facultyuser);
		return "redirect:/sms_admin/show_departments";
	}
		
		// DELETE DEPARTMENT
		@GetMapping("/department_delete/{id}")
		public String DeleteDepartment(Model model,@PathVariable("id") Integer id)
		{
			System.err.println("----- \t DELETE DEPARTMENT \t -----");
			tblDepartment sample = department_repo.findById(id).get();
			department_repo.delete(sample);
			return "redirect:/sms_admin/show_departments";
		}
		
		// ----------------------------------------- START STUDENT USERS -------------------------------------------------
		@GetMapping("/show_student_users")
		public String Admin_Show_StudentUser(Model model)
		{	
			List<tblStudentUser> studentuser_demo = studentuser_repo.findAll();
				
			model.addAttribute("studentuser_list",studentuser_demo);
				
			System.err.println("------ \t Show Admin Courses \t ------");
			return "/admin/studentusers/ShowStudentUsers";
		}
		
		// NEW STUDENTUSERS
		@GetMapping("/studentuser_new")
		public String NewStudentUser(Model model)
		{
			System.err.println("----- \t NEW DEPARTMENT \t ------");
					
			model.addAttribute("studentuser_new_obj",new tblStudentUser());
					
			return "admin/studentusers/New_StudentUser";
					
		}
		
		
		@PostMapping("/save_new_studentuser")
		public String saveNewStudentUser(@Valid @ModelAttribute tblStudentUser new_studentuser,BindingResult binding_result) 
		{
			studentuser_repo.save(new_studentuser);
			
			return "redirect:/sms_admin/show_student_users";
		}
		
		// UPDATE STUDENT USER
		@GetMapping("/studentuser_edit/{id}")
		public String showEditStudentUser(Model model,@PathVariable("id") Integer id)
		{
			tblStudentUser studentuser_sample = studentuser_repo.findById(id).get();
			
			System.err.println("---- \t Edit Student User \t ----");
			
			studentuser_demo = studentuser_sample;
			
			model.addAttribute("studentuser_edit_obj",studentuser_sample);
			
			return "admin/studentusers/Edit_StudentUser";
		}
				
		// UPDATE STUDENT USER
		@PostMapping("/save_edit_studentuser")
		public String saveStudentUser(@Valid @ModelAttribute tblStudentUser edit_studentuser_obj,BindingResult binding_result) 
		{
			System.out.println("--- \t SAVE EDIT \t ---");
			System.out.println(edit_studentuser_obj);
			
			if(binding_result.hasErrors())
			{
				System.err.println("--- Binding Err ---");	
				return "faculty/new_facultyuser_Form";
			}
			crud_users.updateStudentUser(edit_studentuser_obj,studentuser_demo.getId());
			
				
				return "redirect:/sms_admin/show_student_users";
			}
			
		// DELETE STUDENT USER
		@GetMapping("/studentuser_delete/{id}")
		public String DeleteStudentUser(Model model,@PathVariable("id") Integer id)
		{
			System.err.println("----- \t DELETE STUDENT USER \t ----");
			
			tblStudentUser sample = studentuser_repo.findById(id).get();
			studentuser_repo.delete(sample);
			
			return "redirect:/sms_admin/show_student_users";
		}
		
		// ----------------------------------------- START APPLICANT USERS -------------------------------------------------
		@GetMapping("/show_applicant_users")
		public String Admin_Show_ApplicantUser(Model model)
		{	
			List<tblEnrollCourse> enrollcourse_list = enrollcourse_repo.findAll();
			model.addAttribute("enrollcourse_list",enrollcourse_list);
			System.err.println("------ \t Show APPLICANT INFO \t ------");
			return "/admin/applicantusers/ShowApplicantUsers";
		}
		
		// NEW STUDENTUSERS
		@GetMapping("/applicantuser_new")
		public String NewApplicantUser(Model model)
		{
			System.err.println("----- \t NEW DEPARTMENT \t ------");
			model.addAttribute("applicantuser_new_obj",new tblApplicantUser());
			return "admin/applicantusers/New_ApplicantUser";
		}
		
		@PostMapping("/save_new_applicantuser")
		public String saveNewApplicantUser(@Valid @ModelAttribute tblApplicantUser new_applicantuser,BindingResult binding_result) 
		{
			applicantuser_repo.save(new_applicantuser);
					
			return "redirect:/sms_admin/show_applicant_users";
		}
		
		// UPDATE APPLICANT USER
		@GetMapping("/applicantuser_edit/{id}")
		public String showEditApplicantUser(Model model,@PathVariable("id") Integer id)
		{
			tblApplicantUser applicantuser_sample = applicantuser_repo.findById(id).get();
			applicantuser_demo = applicantuser_sample;
			
			model.addAttribute("applicantuser_edit_obj",applicantuser_sample);
					
			return "admin/applicantusers/Edit_ApplicantUser";
		}
						
		// UPDATE STUDENTUSER
		@PostMapping("/save_edit_applicantuser")
		public String saveApplicantUser(@Valid @ModelAttribute tblApplicantUser edit_applicantuser_obj,BindingResult binding_result) 
		{
			System.out.println("--- \t SAVE EDIT \t ---");
			
			if(binding_result.hasErrors())
			{
				System.err.println("--- Binding Err ---");	
				return "faculty/new_facultyuser_Form";
			}
						
			String dbURL = "jdbc:mysql://localhost:3306/student_management_system?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
			String username = "root";
			String password = "root";
						 
			try {
						 
			Connection conn = DriverManager.getConnection(dbURL, username, password);
						 
			 if (conn != null) {
			  System.out.println("------ \t Connected  \t ------");
			}
						    
			System.out.println(edit_applicantuser_obj);
						    
			String sql = "UPDATE tblStudentUser SET firstname='"+ edit_applicantuser_obj.getFirstname() +"' , lastname='" + edit_applicantuser_obj.getLastname() +"' , birthdate='" + edit_applicantuser_obj.getBirthdate()  +"' , gender='" + edit_applicantuser_obj.getGender() +"' , address='" + edit_applicantuser_obj.getAddress()  +"' , mobile='" + edit_applicantuser_obj.getMobile()  +"' , email='" + edit_applicantuser_obj.getEmail() +"' WHERE id="+ applicantuser_demo.getId();
						    
			PreparedStatement statement = conn.prepareStatement(sql);
						     
			int rowsUpdated = statement.executeUpdate();
						    
			if (rowsUpdated > 0) {
				 System.out.println("An existing user was updated successfully!");
			}
			} 
			catch (SQLException ex)
			{
				 ex.printStackTrace();
			}
						
			return "redirect:/sms_admin/show_applicant_users";
			}
				
			
		// DELETE DEPARTMENT
		@GetMapping("/applicantuser_delete/{id}")
		public String DeleteApplicantUser(Model model,@PathVariable("id") Integer id)
		{
			System.err.println("----- \t REJECT APPLICANT USER \t -----");
			
			tblEnrollCourse applicantuser_sample = enrollcourse_repo.findById(id).get();
			enrollcourse_repo.delete(applicantuser_sample);
			return "redirect:/sms_admin/show_applicant_users";
		}
				
		
		// ----------------------------------------- START LEAVE -------------------------------------------------
		@GetMapping("/pending_faculty_user_leave")
		public String Show_Leave(Model model)
		{	
			System.err.println("------ \t Show APPLICANT INFO \t ------");
			
			List<tblEnrollLeave> facultyuser_enrollleave_list = facultyuser_enrollleave_repo.findAll();
			System.out.println(facultyuser_enrollleave_list);
			
			model.addAttribute("enrollleave_list",facultyuser_enrollleave_list);
			return "/admin/faculty/Show_Leave";
		}
		
		// APPROVE LEAVE
		@GetMapping("/save_leave/{id}")
		public String Save_Leave(Model model,@PathVariable("id") Integer id)
		{
			tblEnrollLeave enroll_leave_obj = facultyuser_enrollleave_repo.findById(id).get();
			
			System.err.println("----- \t APPROVE/SAVE LEAVE \t -----");
			System.out.println(enroll_leave_obj);
			
			tblFaculty_Leave faculty_leave_obj = new tblFaculty_Leave(enroll_leave_obj.getStartdate(),enroll_leave_obj.getEnddate());
			faculty_leave_obj.setTblfacultyuser_tblfacultyleave(enroll_leave_obj.getTblfacultyuser_tblfacultyenrollleave());
			
			facultyuser_enrollleave_repo.delete(enroll_leave_obj);
			
			facultyuser_leave_repo.save(faculty_leave_obj);
			
			return "redirect:/sms_admin/pending_faculty_user_leave";
		}
		
		// APPROVE LEAVE
		@GetMapping("/delete_leave/{id}")
		public String Reject_Leave(Model model,@PathVariable("id") Integer id)
		{
			tblEnrollLeave enroll_leave_obj = facultyuser_enrollleave_repo.findById(id).get();
	
			System.err.println("----- \t APPROVE/SAVE LEAVE \t -----");
			System.out.println(enroll_leave_obj);
			
			facultyuser_enrollleave_repo.delete(enroll_leave_obj);
					
			return "redirect:/sms_admin/pending_faculty_user_leave";
		}
		
		
		// PRINT STUDENT USERS
		@GetMapping("/print_student")
		public String Print_Students(Model model) throws IOException
		{
			System.err.println("---- Print Student -----");
			
			List<tblStudentGrades> student_grades_list = crud_studentgrades.writeCSV();
			
			String data_csv_path = "C:\\Users\\pluto\\Documents\\Workspace\\Sprint_Tools_Suite_Workspace\\Student_Management_System_Version_ii\\src\\main\\resources\\static\\data_record\\student_grades_data.csv";
			
			BufferedWriter csvWriter = new BufferedWriter(new FileWriter(data_csv_path));

	        try {
				csvWriter.append("Name");
				csvWriter.append(",");
				csvWriter.append("CourseName");
				csvWriter.append(",");
				csvWriter.append("Semester");
				csvWriter.append(",");
				csvWriter.append("Grade");
				csvWriter.append("\n");

	            //Write a new student object list to the CSV file
				for (tblStudentGrades student_grades_obj : student_grades_list) 
				{
					System.out.println(student_grades_obj);
					
					tblStudents student_obj= student_grades_obj.getTblstudentgrades_tblstudents();
					tblCourses course_obj = student_grades_obj.getTblstudentgrades_tblcourses();
					
					String fullname = student_obj.getFirstName().replace(',', ' ') + " " + student_obj.getMiddelName() + " " + student_obj.getLastName();
					String coursename = course_obj.getCoursename();
					String grade = student_grades_obj.getGrade();
					int semester = student_grades_obj.getSemester();
					
					csvWriter.append(fullname);
					csvWriter.append(",");
					csvWriter.append(coursename);
					csvWriter.append(",");
					csvWriter.append(String.valueOf(semester));
					csvWriter.append(",");
					csvWriter.append(grade);
					csvWriter.append("\n");
				}
	            System.out.println("CSV file was created successfully !!!"); 
	        } 
	        catch (Exception e) 
	        {
	            System.out.println("Error in CsvFileWriter !!!");
	            e.printStackTrace();
	        } 
	        finally
	        {
	            try {
	            	csvWriter.flush();
	            	csvWriter.close();
	            } catch (IOException e) 
	            {
	                System.out.println("Error while flushing/closing fileWriter !!!");
	                e.printStackTrace();
	            }
	        }
			return "redirect:/sms_admin/show_student_users";
		}
}
