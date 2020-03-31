package DAL;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.sms.model.*;
import com.sms.repository.tblCourses_Repository;
import com.sms.repository.tblStudents_Repository;

public class CRUD_StudentGrades {

	String dbURL = "jdbc:mysql://localhost:3306/student_management_system_version_ii?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
	String sql_username = "root";
	String sql_password = "root";
	@Autowired 
	tblStudents_Repository mainstudent_repo;
	@Autowired 
	tblCourses_Repository course_repository;
	
	public List<Integer> getCourseCode(int studentID)
	{
		List<Integer> return_result = new ArrayList<Integer>();
				
		try {
			Connection conn = DriverManager.getConnection(dbURL, sql_username, sql_password);
				 
			if (conn != null)
			{
				System.out.println("Connected");
			}
				    
			String sql = "select * from tblstudentgrades where tblstudentgrades_tblstudents_id="+ studentID;
					
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			 
			while (result.next())
			{
				 int temp_id  = result.getInt("tblstudentgrades_tblcourses_id");
				 return_result.add(temp_id);
			} 
		}
			catch (SQLException ex) 
			{
			    ex.printStackTrace();
			}
		
		return return_result;
	}
	
	public List<tblStudentGrades> getStudentGrades_Courses(String student_username)
	{
		List<tblStudentGrades> final_result = new ArrayList<tblStudentGrades>();
		
		   try {
				Connection conn = DriverManager.getConnection(dbURL, sql_username, sql_password);
					 
				if (conn != null)
				{
					System.out.println("Connected");
				}
					    
				String sql = "select * from tblcourses c join tblstudentgrades stu_grade on stu_grade.tblstudentgrades_tblcourses_id=c.id join tblstudents stu on stu_grade.tblstudentgrades_tblstudents_id=stu.id where stu.studentid='" + student_username + "'";
	
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(sql);
				 
				while (result.next())
				{
					String coursename = result.getString("coursename");
					String coursecode = result.getString("coursecode");
					String courseunit = result.getString("courseunit");
					String grade = result.getString("grade");
					
					int semester = result.getInt("semester");
					int level = result.getInt("level");
					int faculty_id = result.getInt("facultytypeid");
					
				    tblCourses temp_course = new tblCourses(coursecode,coursename,courseunit);
				    
				    tblStudentGrades temp_studentgrades = new tblStudentGrades(grade,semester,level,faculty_id);
				    temp_studentgrades.setTblstudentgrades_tblcourses(temp_course);
				    
				    final_result.add(temp_studentgrades);
				} 
			}
				catch (SQLException ex) 
				{
				    ex.printStackTrace();
				}
		
		return final_result;
	}
	

	 public List<tblStudentGrades> getAllStudentGrades(int department_id)
		{
			List<tblStudentGrades> student_grades_lis = new ArrayList<tblStudentGrades>();
			
			try 
			{
			    Connection conn = DriverManager.getConnection(dbURL, sql_username, sql_password);
			    
			    if (conn != null) {
			        System.out.println("");
			    }
			    
			    String sql = "select stu_grade.id as grade_id,stu_grade.facultytypeid,stu_grade.grade,stu_grade.level,stu_grade.semester, cour.id as course_id,cour.coursecode,cour.coursename,cour.courseunit,stu.id as student_id,stu.first_name,stu.middel_name,stu.last_name,stu.address,stu.birth_date,stu.degree,stu.email,stu.gender,stu.mobile,stu.studentid from tblstudentgrades stu_grade join tblcourses cour on stu_grade.tblstudentgrades_tblcourses_id=cour.id join tblstudents stu on stu.id=stu_grade.tblstudentgrades_tblstudents_id where cour.tblcoursetbldepartment_id=" + department_id;

			    Statement statement = conn.createStatement();
			    ResultSet result = statement.executeQuery(sql);
			    
			    while (result.next())
			    {
			    	int stu_grade_id = result.getInt("grade_id");
			    	int stu_grade_facultytypeid = result.getInt("facultytypeid");
			    	String stu_grade_grade = result.getString("grade");
			    	int stu_grade_level = result.getInt("level");
			    	int stu_grade_semester = result.getInt("semester");
			    	
			    	int course_id = result.getInt("course_id");
			    	String course_unit = result.getString("courseunit");
			    	String course_name = result.getString("coursename");
			    	String course_code = result.getString("coursecode");
			    	
			    	int student_id = result.getInt("student_id");
			    	String first_name = result.getString("first_name");
			    	String last_name = result.getString("last_name");
			    	String middle_name = result.getString("middel_name");
			    	String address = result.getString("address");
			    	String birthdate = result.getString("birth_date");
			    	String degree = result.getString("degree");
			    	String email = result.getString("email");
			    	String gender = result.getString("gender");
			    	String mobile = result.getString("mobile");
			    	String student_mailid= result.getString("studentid");
			    	
			    	tblStudents temp_student = new tblStudents(student_mailid, first_name, last_name, middle_name,gender,birthdate, degree, address, mobile, email);
			    	temp_student.setId(student_id);
			    	
			    	tblCourses temp_course = new tblCourses(course_code,course_name,course_unit);
			    	temp_course.setId(course_id);
			    	
			    	tblStudentGrades temp_student_grade = new tblStudentGrades(stu_grade_grade,stu_grade_semester,stu_grade_level,stu_grade_facultytypeid);
			    	temp_student_grade.setId(stu_grade_id);
			    	temp_student_grade.setTblstudentgrades_tblcourses(temp_course);
			    	temp_student_grade.setTblstudentgrades_tblstudents(temp_student);
			    	
			    	student_grades_lis.add(temp_student_grade);
			    }
			} 
			catch (SQLException ex) {
			    ex.printStackTrace();
			}
			return student_grades_lis;
		}
	 
	 public List<tblStudentGrades> writeCSV()
	 {
		 List<tblStudentGrades> student_grades_lis = new ArrayList<tblStudentGrades>();
			
			try 
			{
			    Connection conn = DriverManager.getConnection(dbURL, sql_username, sql_password);
			    
			    if (conn != null) {
			        System.out.println("");
			    }
			    
			    String sql = "select stu_grad.id as grad_id,stu_grad.facultytypeid,stu_grad.grade,stu_grad.level,stu_grad.semester,stu.id as stu_id,stu.first_name,stu.middel_name,stu.last_name,stu.address,stu.birth_date,stu.degree,stu.email,stu.gender,stu.mobile,stu.studentid,cour.id as course_id,cour.coursename,cour.coursecode,cour.courseunit from tblstudentgrades stu_grad join tblcourses cour on cour.id=stu_grad.tblstudentgrades_tblcourses_id join tblstudents stu on stu.id=stu_grad.tblstudentgrades_tblstudents_id";
			    Statement statement = conn.createStatement();
			    ResultSet result = statement.executeQuery(sql);
			    
			    while (result.next())
			    {
			    	int course_id = result.getInt("course_id");
			    	String coursename = result.getString("coursename");
			    	String coursecode = result.getString("coursecode");
			    	String courseunit = result.getString("courseunit");
			    	
			    	tblCourses course_obj = new tblCourses(coursecode,coursename,courseunit);
			    	course_obj.setId(course_id);
			    	
			    	int student_id = result.getInt("stu_id");
			    	String firstname = result.getString("first_name");
			    	String middlename = result.getString("middel_name");
			    	String lastname = result.getString("last_name");
			    	String address = result.getString("address");
			    	String birthdate = result.getString("birth_date");
			    	String degree = result.getString("degree");
			    	String email = result.getString("email");
			    	String gender = result.getString("gender");
			    	String mobile = result.getString("mobile");
			    	String student_mailid = result.getString("studentid");
			    	
			    	tblStudents student_obj = new tblStudents(student_mailid, firstname,lastname,middlename,gender,birthdate,degree,address,mobile,email);
			    	student_obj.setId(student_id);
			    	
			    	int grade_id = result.getInt("grad_id");
			    	int facultytypeid  = result.getInt("facultytypeid");
			    	String grade = result.getString("grade");
			    	int level = result.getInt("level");
			    	int semester = result.getInt("semester");
			    	
			    	tblStudentGrades student_grades = new tblStudentGrades(grade,semester,level,facultytypeid);
			    	student_grades.setId(grade_id);
			    	student_grades.setTblstudentgrades_tblcourses(course_obj);
			    	student_grades.setTblstudentgrades_tblstudents(student_obj);
			    	
			    	student_grades_lis.add(student_grades);
			    	
			    }
			} 
			catch (SQLException ex) {
			    ex.printStackTrace();
			}
			
			return student_grades_lis;
	 }
}
