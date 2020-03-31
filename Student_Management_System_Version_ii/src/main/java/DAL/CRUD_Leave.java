package DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sms.model.*;

public class CRUD_Leave 
{
	String dbURL = "jdbc:mysql://localhost:3306/student_management_system_version_ii?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
	String sql_username = "root";
	String sql_password = "root";
	
	public List<tblFaculty_Leave> getLeave_by_facultyuser_id(int facuser_id)
	{
		System.out.println("Search Leave Historical data for faculty user id " + facuser_id);
		
		List<tblFaculty_Leave> faculty_leave_lis = new ArrayList<tblFaculty_Leave>();
		
		try 
		{
		    Connection conn = DriverManager.getConnection(dbURL, sql_username, sql_password);
		    
		    if (conn != null) {
		        System.out.println("");
		    }
		    
		    String sql = "select * from tblfacultyleave where tblfacultyuser_tblfacultyleave_id="+ facuser_id ;

		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    
		    while (result.next())
		    {
		    	Date startdate = result.getDate("startdate");
		    	Date enddate = result.getDate("enddate");
		    	int unique_leaveid = result.getInt("id");
		    	tblFaculty_Leave sample_faculty = new tblFaculty_Leave(startdate,enddate);
		    	sample_faculty.setId(unique_leaveid);
		    	faculty_leave_lis.add(sample_faculty);
		    }
		} 
		catch(SQLException ex) 
		{
		    ex.printStackTrace();
		}
		return faculty_leave_lis;
	}
	
}
