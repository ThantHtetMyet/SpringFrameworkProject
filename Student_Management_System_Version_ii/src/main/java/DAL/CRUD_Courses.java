package DAL;
import java.sql.Connection;
import com.sms.repository.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.sms.model.*;

public class CRUD_Courses 
{
	String dbURL = "jdbc:mysql://localhost:3306/student_management_system_version_ii?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
	String username = "root";
	String password = "root";
	
	@Autowired
	tblCourses_Repository courses_repo;
	
	CRUD_Department crud_department = new CRUD_Department();
	
	public tblCourses getCourseById(int target_id)
	{
		tblCourses sample = new tblCourses();
		try {
			 
		    Connection conn = DriverManager.getConnection(dbURL, username, password);
		 
		    if (conn != null) {
		        System.out.println("Connected");
		    }
		    
		    String sql = "select * from tblCourses where id="+ target_id;

		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    

		    while (result.next())
		    {
		    	String course_name = result.getString("coursename");
		    	
		    	sample.setCoursename(course_name);
		    }
		} 
		catch (SQLException ex) {
		    ex.printStackTrace();
		}
		return sample;
	}
	
	
	public List<tblCourses> getCourses_Department()
	{
		List<tblCourses> final_result = new ArrayList<tblCourses>();
		
		try {
			 
		    Connection conn = DriverManager.getConnection(dbURL, username, password);
		 
		    if (conn != null) {
		        System.out.println("Connected");
		    }
		    
		    String sql = "select * from tblcourses course join tbldepartment department on course.tblcoursetbldepartment_id=department.id";
		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    while (result.next())
		    {
		    	int course_id = result.getInt("id");
		    	String course_name = result.getString("coursename");
		    	String course_unit = result.getString("courseunit");
		    	String course_code = result.getString("coursecode");
		    	
		    	String department_firstName = result.getString("firstname");
		    	int department_id = result.getInt("departmentid");
		    	
		    	tblDepartment sample_department = new tblDepartment(department_id,department_firstName);
		    	
		    	tblCourses sample_courses = new tblCourses(course_name,course_code,course_unit);
		    	sample_courses.setId(course_id);
		    	sample_courses.setTblcoursetbldepartment(sample_department);
		    	
		    	final_result.add(sample_courses);
		    }
		} 
		catch (SQLException ex) {
		    ex.printStackTrace();
		}
		return final_result;
	}
	
	
	public tblCourses getOneCourse_Department(int course_id)
	{
		tblCourses final_result = new tblCourses();
		
		try {
			 
		    Connection conn = DriverManager.getConnection(dbURL, username, password);
		 
		    if (conn != null) {
		        System.out.println("Connected");
		    }
		    
		    String sql = "select * from tblcourses course join tbldepartment department on course.tblcoursetbldepartment_id=department.id where course.id=" + course_id;
		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    while (result.next())
		    {
		    	int temp_course_id = result.getInt("id");
		    	String course_name = result.getString("coursename");
		    	String course_unit = result.getString("courseunit");
		    	String course_code = result.getString("coursecode");
		    	
		    	String department_firstName = result.getString("firstname");
		    	int department_id = result.getInt("departmentid");
		    	
		    	tblDepartment sample_department = new tblDepartment(department_id,department_firstName);
		    	
		    	final_result.setId(temp_course_id);
		    	final_result.setCoursecode(course_code);
		    	final_result.setCoursename(course_name);
		    	final_result.setCourseunit(course_unit);
		    	
		    	final_result.setTblcoursetbldepartment(sample_department);
		    	
		    }
		} 
		catch (SQLException ex) {
		    ex.printStackTrace();
		}
		return final_result;
	}
	
	
	public List<tblCourses> getCoursesByFacultyUserName(String facultyusername)
	{
		List<tblCourses> courses_list = new ArrayList<tblCourses>();
		
		CRUD_FacultyUser crud_facultyuser = new CRUD_FacultyUser();
		
		int faculty_id = crud_facultyuser.getFacultyID_by_username(facultyusername);
		
		try {
			 
		    Connection conn = DriverManager.getConnection(dbURL, username, password);
		 
		    if (conn != null) {
		        System.out.println("Connected");
		    }
		    
		    String sql = "select * from tblcourses tbl_cour join tbldepartment tbl_dept on tbl_cour.tblcoursetbldepartment_id=tbl_dept.id where tbl_dept.id in (select tblfaculty_tbldepartment_id from tblfaculty where id = " + faculty_id +")" ;

		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    

		    while (result.next())
		    {
		    	String course_name = result.getString("coursename");
		    	String course_code = result.getString("coursecode");
		    	String course_unit = result.getString("courseunit");
		    	int course_id = result.getInt("id");
		    	
		    	tblCourses temp_course = new tblCourses(course_code,course_name,course_unit);
		    	temp_course.setId(course_id);
		    	
		    	courses_list.add(temp_course);
		    }
		} 
		catch (SQLException ex) {
		    ex.printStackTrace();
		}
		return courses_list;
	}
	
	public void updateCourseBy_courseid(tblCourses edit_course,int course_id)
	{
		try {
			 
		    Connection conn = DriverManager.getConnection(dbURL, username, password);
		 
		    if (conn != null) {
		        System.out.println("Connected");
		    }
		  
		    Statement statement = conn.createStatement();
		    
		    String query1 = "update tblCourses set coursecode='" + edit_course.getCoursecode() +"',coursename='"+ edit_course.getCoursename() +"',courseunit='" + edit_course.getCourseunit()+"'" +"where id=" + course_id;
		    
		    statement.executeUpdate(query1);
		} 
		catch (SQLException ex) {
		    ex.printStackTrace();
		}
	}
	
	public tblCourses getCourse_by_attendStudent_id(int attend_student_id)
	{
		tblCourses final_result;
		
		try {
			 
		    Connection conn = DriverManager.getConnection(dbURL, "root" , "root");
		 
		    if (conn != null) {
		        System.out.println("Connected");
		    }
		    
		    String sql = "select * from tblCourses where coursename in (select attendcoursename from tblattendstudent where id=" + attend_student_id + ")" ;
		    
		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    
		    
		    String coursename = null, coursecode=null,courseunit = null;
		    int courseId = 0;
		    
		    while (result.next())
		    {
		    	coursename = result.getString("coursename");
		    	coursecode = result.getString("coursecode");
		    	courseunit = result.getString("courseunit");
		    	courseId = result.getInt("id");
		    }
		    
		    final_result = new tblCourses(coursecode,coursename,courseunit);
		    final_result.setId(courseId);
	    	//final_result.setStudentid(studentgmail);
	    	 
		    System.out.println(final_result);
		     
		    return final_result;
		} 
		catch (SQLException ex) {
		    ex.printStackTrace();
		}
		return null;
	}
	
	
	public int getDepartmentID_by_coursename(String coursename)
	{
		int department_id = 0;
		try {
			 
		    Connection conn = DriverManager.getConnection(dbURL, username, password);
		 
		    if (conn != null) {
		        System.out.println("Connected");
		    }
		    
		    String sql = "select * from tblCourses where coursename='"+ coursename + "'";

		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    

		    while (result.next())
		    {
		    	department_id = result.getInt("tblcoursetbldepartment_id");
		    }
		    return department_id;
		} 
		catch (SQLException ex) {
		    ex.printStackTrace();
		}
		return 0;
	}
	
	public void updateCourse(tblCourses old_course,tblCourses new_course)
	{
		try {
			 
		    Connection conn = DriverManager.getConnection(dbURL, username, password);
		 
		    if (conn != null) {
		        System.out.println("Connected");
		    }
		    
		    Statement statement = conn.createStatement();
		    
		    String query1 = "update tblCourses set coursecode='" + new_course.getCoursecode() +"',coursename='"+ new_course.getCoursename() +"',courseunit='" + new_course.getCourseunit()+"',tblcoursetbldepartment_id = "+ new_course.getTransient_course_department() +" where id = " + old_course.getId();
		    
		    statement.executeUpdate(query1);
		} 
		catch (SQLException ex) {
		    ex.printStackTrace();
		}
	}
	
	
	public tblCourses getUpdateCourse(tblCourses old_course,tblCourses new_course)
	{
		tblCourses sample_course = new_course;
		
		tblDepartment target_department = crud_department.getDepartment_by_departmentID(new_course.getTransient_course_department());

		System.out.println("Target Department: " + target_department);
		sample_course.setTblcoursetbldepartment(target_department);
		
		return sample_course;
	}
}
