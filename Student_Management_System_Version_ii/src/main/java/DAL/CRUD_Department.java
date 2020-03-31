package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sms.model.*;

public class CRUD_Department 
{

	String dbURL = "jdbc:mysql://localhost:3306/student_management_system_version_ii?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
	String sql_username = "root";
	String sql_password = "root";
	
	public void updateDepartment(tblDepartment edit_department_obj,int department_id)
	{
		try {
		    Connection conn = DriverManager.getConnection(dbURL, sql_username, sql_password);
		    if (conn != null) {
		        System.out.println("------ \t Connected  \t ------");
		    }
		    
		    String sql = "UPDATE tbldepartment SET departmentid='" + edit_department_obj.getDepartmentid() + "' , firstname='" + edit_department_obj.getFirstname() +"' WHERE id="+ department_id;
		    
		    PreparedStatement statement = conn.prepareStatement(sql);
		     
		    int rowsUpdated = statement.executeUpdate();
		    
		    if (rowsUpdated > 0) {
		        System.out.println("An existing user was updated successfully!");
		    }
		} 
		catch (SQLException ex) {
		    ex.printStackTrace();
		}
		
	}
	
	public tblDepartment getDepartment_by_facultyuser_username(String facultyuser_username)
	{
		tblDepartment sample = new tblDepartment();
		
		try {
		    Connection conn = DriverManager.getConnection(dbURL, sql_username, sql_password);
		 
		    if (conn != null) {
		        System.out.println("Connected");
		    }
		    
		    String sql = "select * from tbldepartment where id in ( select fac.tblfaculty_tbldepartment_id from tblfacultyuser fac_user join tblfaculty fac on fac.id=fac_user.tblfaculty_tblfacultyuser_id where fac_user.username='" + facultyuser_username + "')";
		
		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    
		    while (result.next())
		    {
		    	String firstname = result.getString("firstname");
		    	int departmentid = result.getInt("departmentid");
		    	int unique_departmentid = result.getInt("id");
		    	
		    	sample.setFirstname(firstname);
		    	sample.setDepartmentid(departmentid);
		    	sample.setId(unique_departmentid);
		    }
		} 
		catch (SQLException ex) {
		    ex.printStackTrace();
		}
		return sample;
	}
	
	public tblDepartment getDepartment_by_departmentID(int departmentid)
	{
		System.out.println("DEPARTMENT ID:" + departmentid);
		
		tblDepartment result_department = new tblDepartment();
		
		try {
			  
			  Connection conn = DriverManager.getConnection(dbURL, sql_username, sql_password);
			  
			  if (conn != null) { System.out.println("Connected"); }
			  
			  String query1 = "select * from tbldepartment where departmentid = "+ departmentid;
			  
			  Statement statement = conn.createStatement(); ResultSet result =
			  statement.executeQuery(query1);
			 
			  while (result.next()) 
			  { 
				  int department_unique_id = result.getInt("id");
				  int department_id = result.getInt("departmentid");
				  String department_firstname = result.getString("firstname");
				  
				  result_department.setDepartmentid(department_id);
				  result_department.setId(department_unique_id);
				  result_department.setFirstname(department_firstname);
				  
			  }
			  
			  
			  statement.executeUpdate(query1); } catch (SQLException ex) {
			  ex.printStackTrace(); }
			 
			return result_department;
	}
}
