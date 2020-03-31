package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sms.model.*;

public class CRUD_Enrollcourses {

	String dbURL = "jdbc:mysql://localhost:3306/student_management_system_version_ii?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
	String username = "root";
	String password = "root";

	public List<tblEnrollCourse> getEnrollCourseByUserName(String enroll_username)
	{
		List<tblEnrollCourse> enroll_course_list = new ArrayList<tblEnrollCourse>();
		
		try {
		    Connection conn = DriverManager.getConnection(dbURL, username, password);
		    
		    if (conn != null) {
		        System.out.println("Connected");
		    }
		    
		    String sql = "select * from tblenrollcourse	 where enrollusername='"+ enroll_username + "'";

		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    

		    while (result.next())
		    {
		    	String temp_user_name = result.getString("enrollusername");
		    	String temp_course_name = result.getString("enrollcoursename");
		    	int temp_course_id = result.getInt("id");

				tblEnrollCourse sample = new tblEnrollCourse();
		    	sample.setEnrollcoursename(temp_course_name);
		    	sample.setEnrollusername(temp_user_name);
		    	sample.setId(temp_course_id);
		    	
		    	enroll_course_list.add(sample);
		    }
	    	
		} 
		catch (SQLException ex) {
		    ex.printStackTrace();
		}
		return enroll_course_list;
	}
	
	public List<tblCourses> CompareCourse(List<tblCourses> courses_list,List<tblEnrollCourse> enrollcourse_list)
	{
		List<tblCourses> final_course_list=new ArrayList<tblCourses>();
		boolean flag = false;
		
		for(tblCourses temp_course:courses_list)
		{
			System.out.println("Target =>" + temp_course.getCoursename());
			
			for(tblEnrollCourse temp_enrollcourse:enrollcourse_list)
			{
				System.out.println("Compare =>" + temp_enrollcourse.getEnrollcoursename());
				
				if(temp_course.getCoursename().equals(temp_enrollcourse.getEnrollcoursename()))
				{
					System.out.println("Equal");
					flag = true;
					break;
				}
			}
			if(!flag)
			{
				System.out.println("ADDED");
				final_course_list.add(temp_course);
			}
			else {
				flag = false;
			}
		}
		return final_course_list;
	}
	

	/*
	 * public tblEnrollCourse createEnrollCourseById(int target_id,String
	 * student_username) { String course_name;
	 * 
	 * tblEnrollCourse sample = new tblEnrollCourse();
	 * 
	 * try { Connection conn = DriverManager.getConnection(dbURL, username,
	 * password);
	 * 
	 * if (conn != null) { System.out.println("Connected"); }
	 * 
	 * String sql = "select * from tblCourses where id="+ target_id;
	 * 
	 * Statement statement = conn.createStatement(); ResultSet result =
	 * statement.executeQuery(sql);
	 * 
	 * 
	 * while (result.next()) { course_name = result.getString("coursename"); } }
	 * catch (SQLException ex) { ex.printStackTrace(); } return sample; }
	 */
}
