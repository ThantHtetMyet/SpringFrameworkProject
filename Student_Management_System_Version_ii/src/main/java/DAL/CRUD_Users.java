package DAL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.sms.model.*;

public class CRUD_Users 
{
	String dbURL = "jdbc:mysql://localhost:3306/student_management_system_version_ii?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
	String sql_username = "root";
	String sql_password = "root";
	 
	public void updateStudentUser(tblStudentUser edit_studentuser_obj,int studentuser_id)
	{
		try {
		    Connection conn = DriverManager.getConnection(dbURL, sql_username, sql_password);
		 
		    if (conn != null) {
		        System.out.println("------ \t Connected  \t ------");
		    }
		    
		    System.out.println(edit_studentuser_obj);
		    
		    String sql = "UPDATE tblStudentUser SET username='"+ edit_studentuser_obj.getUsername() +"' , password='" + edit_studentuser_obj.getPassword() +"' WHERE id="+ studentuser_id;
		    
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
	
	public void updateFacultyUser(tblFacultyUser edit_faculty_user,int faculty_user_id)
	{

		try {
		 
		    Connection conn = DriverManager.getConnection(dbURL, sql_username, sql_password);
		 
		    if (conn != null) {
		        System.out.println("Connected");
		    }
		    
		    
		    String sql = "UPDATE tblfacultyuser SET username='" + edit_faculty_user.getUsername() + "' , password='" + edit_faculty_user.getPassword() + "' WHERE id=" + faculty_user_id;
		    
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
}
