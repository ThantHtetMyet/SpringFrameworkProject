package DAL;
import javax.persistence.*;
import com.sms.model.*;

public class DataService {
EntityManager em;

public DataService()
{}
public DataService(EntityManager em)
{
	this.em = em;
}

// One Table
public level createLevel(int level_id,String label)
{
	level sample_level = new level(level_id,label);
	
	/*
	em.getTransaction().begin();
	em.persist(sample_level);
	em.getTransaction().commit();
	*/
	
	return sample_level;
}

// Two Table
public semester createSemester(int semester_id,String label)
{
	semester sample = new semester(semester_id,label);
	/*
	em.getTransaction().begin();
	em.persist(sample);
	em.getTransaction().commit();
	*/
	return sample;
}

// Three Table
public Status createStatus(int status,String label)
{
	Status sample = new Status(status,label);
	/*
	em.getTransaction().begin();
	em.persist(sample);
	em.getTransaction().commit();
	*/
	return sample;
}

// Four Table
public tblAdminUser createtblAdminUser(String username,String password)
{
	tblAdminUser sample = new tblAdminUser(username,password);
	/*
	em.getTransaction().begin();
	em.persist(sample);
	em.getTransaction().commit();
	*/
	return sample;
}

// Five Table
public tblApplicantUser createtblApplicantUser(String firstname, String lastname, String gender, String birthdate, String address,String mobile, String email)
{
	tblApplicantUser sample = new tblApplicantUser(firstname,lastname,gender,birthdate,address,mobile,email);
	/*
	em.getTransaction().begin();
	em.persist(sample);
	em.getTransaction().commit();
	*/
	return sample;
}

// Six Table
public tblDepartment createtblDepartment( int departmentID, String firstName)
{
	tblDepartment sample = new  tblDepartment(departmentID,firstName);
	/*
	em.getTransaction().begin();
	em.persist(sample);
	em.getTransaction().commit();
	*/
	return sample;
}

// Seven Table
public tblGPA createtblGPA(String gpaCalculated)
{
	tblGPA sample = new tblGPA(gpaCalculated);
	/*
	em.getTransaction().begin();
	em.persist(sample);
	em.getTransaction().commit();
	*/
	return sample;
}

// Eight Table
public tblFacultyUser createFacultyUser(String username,String password,tblFaculty faculty_obj)
{
	tblFacultyUser sample = new tblFacultyUser(username,password);
	sample.setTblfaculty_tblfacultyuser(faculty_obj);
	/*
	em.getTransaction().begin();
	em.persist(sample);
	em.getTransaction().commit();
	*/
	return sample;
}

// Nine Table
public tblStudentUser createStudentUser(String username,String password)
{
	tblStudentUser sample = new tblStudentUser(username,password);
	/*
	em.getTransaction().begin();
	em.persist(sample);
	em.getTransaction().commit();
	*/
	return sample;
}

// Ten Table
public tblStudents createtblStudents(String studentid,String firstName, String lastName, String middelName, String gender, String birthDate,String degree, String address, String mobile, String email,level sample_level,semester sample_semester,tblGPA sample_gpa,Status sample_status)
{
	tblStudents sample_student = new tblStudents(studentid,firstName,lastName,middelName,gender,birthDate,degree,address,mobile,email);
	sample_student.setStudents__level(sample_level);
	sample_student.setStudents_semester(sample_semester);
	sample_student.setStudents_status(sample_status);
	sample_student.setStudents_tlbgpa(sample_gpa);
	/*
	em.getTransaction().begin();
	em.persist(sample_student);
	em.getTransaction().commit();
	*/
	return sample_student;
}

// Eleven Table
public tblFaculty createtblFaculty(String firstName,String lastName,String middleName,tblDepartment sample_department)
{
	tblFaculty sample_faculty = new tblFaculty(firstName,lastName,middleName);
	sample_faculty.setTblfaculty_tbldepartment(sample_department);
	/*
	em.getTransaction().begin();
	em.persist(sample_faculty);
	em.getTransaction().commit();
	*/
	return sample_faculty;
}

// Twelve Table
public tblCourses createtblCourses(String coursecode, String coursename, String courseunit,tblDepartment sample_department)
{
	tblCourses sample_courses = new tblCourses(coursecode,coursename,courseunit);
	sample_courses.setTblcoursetbldepartment(sample_department);
	/*
	em.getTransaction().begin();
	em.persist(sample_courses);
	em.getTransaction().commit();
	*/
	return sample_courses;
}

// Thirteen Table
public tblStudentGrades createtblStudentGrades(String grade, int semester, int level, int facultytypeid,tblCourses sample_course,tblStudents sample_students)
{
	tblStudentGrades sample = new tblStudentGrades(grade,semester,level,facultytypeid);
	sample.setTblstudentgrades_tblcourses(sample_course);
	sample.setTblstudentgrades_tblstudents(sample_students);
	
	return sample;
}
}
