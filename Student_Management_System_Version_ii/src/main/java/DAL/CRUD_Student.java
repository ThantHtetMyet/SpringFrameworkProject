package DAL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.model.*;
import com.sms.repository.tblStudents_Repository;

import Ajax.AjaxResponseBodyStudent;

@Service
public class CRUD_Student {
	
	AjaxResponseBodyStudent result = new AjaxResponseBodyStudent();

	@Autowired
	tblStudents_Repository student_repo;
	
	public CRUD_Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	String dbURL = "jdbc:mysql://localhost:3306/student_management_system_version_ii?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
	String sql_username = "root";
	String sql_password = "root";
	
	public AjaxResponseBodyStudent getStudentById(String username,String password)
	{
		String studentuser_username = null;
		int studentuser_id = 0;
		String studentuser_password = null;
		
		System.out.println("UserName = \t "+ username);
		System.out.println("Password = \t" + password);
		if(username=="" && password=="")
		{
			result.setMsg("empty_err");
		}
		else if(username=="" && password!="")
		{
			result.setMsg("username_empty_err");
		}
		else if(username!="" && password=="")
		{
			result.setMsg("password_empty_err");
		}
		else 
		{
			tblStudentUser sample = new tblStudentUser();
			
			try {
			 
			    Connection conn = DriverManager.getConnection(dbURL, sql_username, sql_password);
			 
			    if (conn != null) {
			        System.out.println("Connected");
			    }
			    
			    String sql = "select * from tblStudentUser where username = '"+ username +"'";

			    Statement statement = conn.createStatement();
			    ResultSet result = statement.executeQuery(sql);
		 
			    while (result.next())
			    {
			    	studentuser_id  = result.getInt("id");
			    	studentuser_username = result.getString("username");
			    	studentuser_password = result.getString("password");
			    }
			    sample.setId(studentuser_id);
		    	sample.setUsername(studentuser_username);
		    	sample.setPassword(studentuser_password);
			} 
			catch (SQLException ex) {
			    ex.printStackTrace();
			}
			
			System.out.println("********************");
			System.out.println(sample.getPassword());
			System.out.println("----> \t" + password);
			
			if(studentuser_password==null)
			{
				System.err.println("user_err");
				result.setMsg("user_err");
			}
			
			else if(!sample.getPassword().equals(password))
			{
				System.err.println("password_err");
				result.setMsg("password_err");
			}
			else if(sample.getPassword().equals(password))
			{
				System.err.println("match");
				result.setMsg("match");
				result.setResult(sample);
			}
		}
		return result;
	}
	
	public tblStudents getStudents_by_attendStudent_id(int attend_student_id)
	{
		tblStudents final_result;
		
		try {
			 
		    Connection conn = DriverManager.getConnection(dbURL, sql_username, sql_password);
		 
		    if (conn != null) {
		        System.out.println("Connected");
		    }
		    
		    String sql = "select * from tblstudents where studentid in (select attendusername from tblattendstudent where id = " + attend_student_id + ")" ;

		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    
		    String student_address = null,birth_date = null,degree = null,email = null,first_name = null,gender = null,last_name = null,middle_name = null,mobile = null,studentgmail ="";
		    int student_id = 0;
		    while (result.next())
		    {
		    	  student_id = result.getInt("id");
		    	  student_address = result.getString("address");
		    	  birth_date = result.getString("birth_date");
		    	  degree = result.getString("degree");
		    	  email = result.getString("email");
		    	  first_name = result.getString("first_name");
		    	  gender = result.getString("gender");
		    	  last_name = result.getString("last_name");
		    	  middle_name = result.getString("middel_name");
		    	  mobile = result.getString("mobile");
		    	  studentgmail = result.getString("studentid");
		    }
		    final_result = new tblStudents(first_name, last_name, middle_name,gender,birth_date,degree, student_address,mobile, email);
	    	final_result.setId(student_id);
	    	final_result.setStudentid(studentgmail);
	    	 
		    System.out.println(final_result);
		     
		    return final_result;
		} 
		catch (SQLException ex) {
		    ex.printStackTrace();
		}
		return null;
	}
}
