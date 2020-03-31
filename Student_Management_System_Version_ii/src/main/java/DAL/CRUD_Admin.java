package DAL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.sms.model.*;
import Ajax.*;

public class CRUD_Admin 
{
	String dbURL = "jdbc:mysql://localhost:3306/student_management_system_version_ii?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
	String username = "root";
	String password = "root";

public CRUD_Admin() {
	super();
	// TODO Auto-generated constructor stub
}

// DEPARTMENT TABLE
public tblDepartment getDepartment(int target_id)
{
	tblDepartment sample = new tblDepartment();
	
	try {
	 
	    Connection conn = DriverManager.getConnection(dbURL, username, password);
	 
	    if (conn != null) {
	        System.out.println("Connected");
	    }
	    
	    String sql = "select * from tblDepartment where departmentid="+ target_id;

	    Statement statement = conn.createStatement();
	    ResultSet result = statement.executeQuery(sql);
 

	    while (result.next())
	    {
	    	int unique_department_id = result.getInt("id");
	    	String department_firstname = result.getString("firstname");
	    	
	    	sample.setDepartmentid(target_id);
	    	sample.setFirstname(department_firstname);
	    	sample.setId(unique_department_id);
	    }
	} 
	catch (SQLException ex) {
	    ex.printStackTrace();
	}
	return sample;
}


//DEPARTMENT TABLE
public tblFaculty getFaculty(int target_id)
{
	tblFaculty sample = new tblFaculty();
	
	try {
	 
	    Connection conn = DriverManager.getConnection(dbURL, username, password);
	 
	    if (conn != null) {
	        System.out.println("Connected");
	    }
	    
	    String sql = "select * from tblFaculty where id="+ target_id;

	    Statement statement = conn.createStatement();
	    ResultSet result = statement.executeQuery(sql);


	    while (result.next())
	    {
	    	int unique_faculty_id = result.getInt("id");
	    	String faculty_firstname = result.getString("firstname");
	    	String faculty_lastname = result.getString("lastname");
	    	String faculty_middlename = result.getString("middlename");
	    	
	    	sample.setId(unique_faculty_id);
	    	sample.setFirstname(faculty_firstname);
	    	sample.setMiddlename(faculty_middlename);
	    	sample.setLastname(faculty_lastname);
	    	
	    }
	} 
	catch (SQLException ex) {
	    ex.printStackTrace();
	}
	return sample;
}

public void DeleteCourse(String target_id)
{
	try {
		 
	    Connection conn = DriverManager.getConnection(dbURL, username, password);
	 
	    if (conn != null) {
	        System.out.println("Connected");
	    }

	    // DELETE
	    System.err.println("Target ID=>"+target_id);
		String sql = "DELETE FROM tblCourses WHERE coursename='" + target_id +"'";
		 
		PreparedStatement statement = conn.prepareStatement(sql);
		int rowsDeleted = statement.executeUpdate();
		
		if (rowsDeleted > 0) {
		    System.out.println("A user was deleted successfully!");
		}
	} 
	catch (SQLException ex) {
	    ex.printStackTrace();
	}
}


// ------------------------- AJAX --------------------------------------------------------

public AjaxResponseBodyAdmin getAdminUser(String admin_username,String admin_password)
{
	AjaxResponseBodyAdmin result = new AjaxResponseBodyAdmin();
	tblAdminUser admin_user = new tblAdminUser();
	int admin_user_id = 0;
	String admin_user_username ="";
	String admin_user_password = "";
	
	if(admin_username=="" && admin_password=="")
	{
		result.setMsg("empty_err");
	}
	else if(admin_username=="" && admin_password!="")
	{
		result.setMsg("username_empty_err");
	}
	else if(admin_username!="" && admin_password=="")
	{
		result.setMsg("password_empty_err");
	}
	else 
	{
		
		try {
		    Connection conn = DriverManager.getConnection(dbURL, username, password);
		
		    if (conn != null) 
		    {
		        System.out.println("Connected");
		    }
		    
		    String sql = "select * from tblAdminUser where username = '"+ admin_username +"'";
		    Statement statement = conn.createStatement();
		    ResultSet result_two = statement.executeQuery(sql);
		    
		    while (result_two.next())
		    {
		    	admin_user_id  = result_two.getInt("id");
		    	admin_user_username = result_two.getString("username");
		    	admin_user_password = result_two.getString("password");
		    }
		    
		    admin_user.setId(admin_user_id);
		    admin_user.setUsername(admin_user_username);
		    admin_user.setPassword(admin_user_password);
		} 
		catch (SQLException ex) 
		{
		    ex.printStackTrace();
		}
		
		if(admin_user_username == "")
		{
			result.setMsg("user_err");
		}
		else if(!admin_user.getPassword().equals(admin_password))
		{
			result.setMsg("password_err");
		}
		else if(admin_user.getPassword().equals(admin_password))
		{
			result.setMsg("match");
			result.setResult(admin_user);
		}
	}
	
	System.out.println("AJAX Response-> \t"+result);
	return result;
}
}
