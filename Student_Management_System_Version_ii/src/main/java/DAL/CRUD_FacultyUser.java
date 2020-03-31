package DAL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import com.sms.model.*;
import com.sms.repository.*;

public class CRUD_FacultyUser 
{
	String dbURL = "jdbc:mysql://localhost:3306/student_management_system_version_ii?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
	String sql_username = "root";
	String sql_password = "root";

	CRUD_Faculty crud_faculty = new CRUD_Faculty();
	
	@Autowired 
	tblFaculty_Repository facultory_repo;
	
	public tblFacultyUser getFacultyByFaculty_ID(int fac_id)
	{
		tblFacultyUser facultyuser_object = new tblFacultyUser();
		
		try 
		{
		    Connection conn = DriverManager.getConnection(dbURL, sql_username, sql_password);
		    
		    if (conn != null) {
		        System.out.println("");
		    }
		    
		    String sql = "select * from tblFacultyuser where id="+ fac_id ;

		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    
		    while (result.next())
		    {
		    	int faculty_id = result.getInt("tblfaculty_tblfacultyuser_id");
		    	String faculty_username = result.getString("username");
		    	int facultyuser_id = result.getInt("id");
		    	String facultyuser_password = result.getString("password");
		    	
		    	tblFaculty temp_faculty_obj = crud_faculty.getFacultyByID(faculty_id);
		    	
		    	System.err.println(faculty_id);
		    	facultyuser_object.setId(facultyuser_id);
		    	facultyuser_object.setPassword(facultyuser_password);
		    	facultyuser_object.setUsername(faculty_username);
		    	facultyuser_object.setTblfaculty_tblfacultyuser(new tblFaculty(temp_faculty_obj.getFirstname(),temp_faculty_obj.getLastname(),temp_faculty_obj.getMiddlename()));
		    	
		    }
		} 
		catch (SQLException ex) {
		    ex.printStackTrace();
		}
		return facultyuser_object;
	}
	
	public int getFacultyID_by_username(String faculty_username)
	{
		int faculty_id = 0;
		
		try 
		{
		    Connection conn = DriverManager.getConnection(dbURL, sql_username, sql_password);
		    
		    if (conn != null) {
		        System.out.println("");
		    }
		    
		    String sql = "select * from tblFacultyuser where username = '"+ faculty_username + "'";

		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    
		    while (result.next())
		    {
		    	faculty_id = result.getInt("tblfaculty_tblfacultyuser_id");
		    }
		} 
		catch (SQLException ex) {
		    ex.printStackTrace();
		}
		return faculty_id;
	}
	
	public int getFacultyUserID_by_username(String faculty_username)
	{
		int facultyuser_id = 0;
		
		try 
		{
		    Connection conn = DriverManager.getConnection(dbURL, sql_username, sql_password);
		    
		    if (conn != null) {
		        System.out.println("");
		    }
		    
		    String sql = "select * from tblFacultyuser where username = '"+ faculty_username + "'";

		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);
		    
		    while (result.next())
		    {
		    	facultyuser_id = result.getInt("id");
		    }
		} 
		catch (SQLException ex) {
		    ex.printStackTrace();
		}
		return facultyuser_id;
	}
}
