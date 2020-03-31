package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.sms.model.*;
import Ajax.*;

public class CRUD_Faculty 
{
	String dbURL = "jdbc:mysql://localhost:3306/student_management_system_version_ii?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
	String sql_username = "root";
	String sql_password = "root";

	// ------------------------- AJAX --------------------------------------------------------

	public AjaxResponseBodyFaculty getFacultyUser(String faculty_username,String faculty_password)
	{
		AjaxResponseBodyFaculty result = new AjaxResponseBodyFaculty();
		tblFacultyUser faculty_user = new tblFacultyUser();
		int faculty_user_id = 0;
		String faculty_user_username ="";
		String faculty_user_password = "";
		
		if(faculty_username=="" && faculty_password=="")
		{
			result.setMsg("empty_err");
		}
		else if(faculty_username=="" && faculty_password!="")
		{
			result.setMsg("username_empty_err");
		}
		else if(faculty_username!="" && faculty_password=="")
		{
			result.setMsg("password_empty_err");
		}
		else 
		{
			
			try {
			    Connection conn = DriverManager.getConnection(dbURL, sql_username, sql_password);
			
			    if (conn != null) 
			    {
			        System.out.println("Connected");
			    }
			    
			    String sql = "select * from tblFacultyUser where username = '"+ faculty_username +"'";
			    Statement statement = conn.createStatement();
			    ResultSet result_two = statement.executeQuery(sql);
			    
			    while (result_two.next())
			    {
			    	faculty_user_id  = result_two.getInt("id");
			    	faculty_user_username = result_two.getString("username");
			    	faculty_user_password = result_two.getString("password");
			    }
			    
			    faculty_user.setId(faculty_user_id);
			    faculty_user.setUsername(faculty_user_username);
			    faculty_user.setPassword(faculty_user_password);
			} 
			catch (SQLException ex) 
			{
			    ex.printStackTrace();
			}
			
			if(faculty_user_username == "")
			{
				result.setMsg("user_err");
			}
			else if(!faculty_user.getPassword().equals(faculty_password))
			{
				result.setMsg("password_err");
			}
			else if(faculty_user.getPassword().equals(faculty_password))
			{
				result.setMsg("match");
				result.setResult(faculty_user);
			}
		}
		System.out.println("AJAX Response-> \t" + result);
		return result;
	}
	
	public tblFaculty getFacultyByID(int faculty_id)
	{
		tblFaculty sample = new tblFaculty();
		
		try 
		{
		    Connection conn = DriverManager.getConnection(dbURL, sql_username, sql_password);
		 
		    if (conn != null) {
		        System.out.println("Connected");
		    }
		    
		    String sql = "select * from tblFaculty where id="+ faculty_id ;

		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    
		    while (result.next())
		    {
		    	String firstname = result.getString("firstname");
		    	String middlename = result.getString("middlename");
		    	String lastname = result.getString("lastname");
		    	
		    	sample.setId(faculty_id);
		    	sample.setFirstname(firstname);
		    	sample.setMiddlename(middlename);
		    	sample.setLastname(lastname);
		    }
		} 
		catch (SQLException ex) {
		    ex.printStackTrace();
		}
		
		return sample;
	}
}
