package com.sms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.sms.repository.*;
/*import com.sms.model.*;
import org.slf4j.Logger;
import com.sms.repository.*;
import com.sms.model.*;
import DAL.*;
import org.slf4j.LoggerFactory;
*/

@ComponentScan({ "DAL","Ajax","Session","com.sms.controller","com.sms.model","com.sms.repository"})
@SpringBootApplication
@EnableJpaAuditing
public class StudentManagementSystemVersionIiApplication {
	
	//log file 
	//private static final Logger logger = LoggerFactory.getLogger(StudentManagementSystemVersionIiApplication.class);	

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemVersionIiApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(tblAttend_Student_Repository attend_student_repo,tblDepartment_Repository department_repo,tblCourses_Repository course_repo,tblStudentGrades_Repository grade_repo,tblFaculty_Repository faculty_repo,tblStudents_Repository student_repo,level_Repository level_repo,semester_Repository semester_repo,tblGPA_Repository gpa_repo,Status_Repository status_repo,tblStudentUser_Repository studentuser_repo,tblFacultyUser_Repository facultyuser_repo,tblAdminUser_Repository adminuser_repo,tblApplicantUser_Repository applicantuser_repo)
	{
		/*
		System.err.println("\t \t \t Data Initializing....... \t \t \t");
		
		logger.info("\t \t \t Data Initializing....... \t \t \t");
		
		DataService service = new DataService();
		// 1. LEVEL 
		level level_obj_one = service.createLevel(0, "Bad");
		level level_obj_two = service.createLevel(1, "Good");
		level level_obj_three = service.createLevel(2, "Best");
		level_repo.save(level_obj_one);
		level_repo.save(level_obj_three);
		level_repo.save(level_obj_two);
		// 2. SEMESTER
		semester semester_obj_one = service.createSemester(78, "First");
		semester semester_obj_two = service.createSemester(13, "Second");
		semester semester_obj_three = service.createSemester(54, "Third");
		semester_repo.save(semester_obj_one);
		semester_repo.save(semester_obj_two);
		semester_repo.save(semester_obj_three);
		// 3. TBL GPA
		tblGPA gpa_obj_one = service.createtblGPA("4.03");
		tblGPA gpa_obj_two = service.createtblGPA("3.01");
		tblGPA gpa_obj_three = service.createtblGPA("4.9");
		gpa_repo.save(gpa_obj_one);
		gpa_repo.save(gpa_obj_two);
		gpa_repo.save(gpa_obj_three);
		// 4. STATUS
		Status status_obj_one = service.createStatus(1,"Low");
		Status status_obj_two = service.createStatus(2,"Middle");
		Status status_obj_three = service.createStatus(3,"High");
		status_repo.save(status_obj_one);
		status_repo.save(status_obj_two);
		status_repo.save(status_obj_three);
		// 5. TBL STUDENTS
		tblStudents student_obj_one = service.createtblStudents("thanthtetmyet1994@gmail.com","Thant", "Myet", "Htet", "male", "26/8/1994", "Master", "Clementi", "83816401", "thanthtetmyet1994@gmail.com", level_obj_two, semester_obj_two, gpa_obj_one, status_obj_two);
		tblStudents student_obj_two = service.createtblStudents("wityimon1995@gmail.com",",Ma", "Mon", "Wityi", "female", "21/5/1995", "Master", "PyinOoLwin", "095127360", "wityimon1995@gmail.com", level_obj_one, semester_obj_one, gpa_obj_three, status_obj_one);
		tblStudents student_obj_three = service.createtblStudents("kaungyazar1994@gmail.com","Kaung", "Zar", "Yar", "male", "2/3/1994", "Bachelor", "Yangon", "095024857", "kaungyarzar1994@gmail.com", level_obj_three, semester_obj_three, gpa_obj_three, status_obj_three);
		student_repo.save(student_obj_one);
		student_repo.save(student_obj_two);
		student_repo.save(student_obj_three);
		// 6. TBL Student USER
		tblStudentUser studentuser_one = service.createStudentUser("thanthtetmyet1994@gmail.com", "1234");
		tblStudentUser studentuser_two = service.createStudentUser("wityimon1995@gmail.com", "1234");
		tblStudentUser studentuser_three = service.createStudentUser("kaungyazar1994@gmail.com", "1234");
		studentuser_repo.save(studentuser_one);
		studentuser_repo.save(studentuser_two);
		studentuser_repo.save(studentuser_three);
		//  7. TBL ADMIN USER
		tblAdminUser adminuser_one = service.createtblAdminUser("aungwin@gmail.com", "1234");
		adminuser_repo.save(adminuser_one);
		// 8. TBL DEPARTMENT
		tblDepartment department_obj_one = service.createtblDepartment(481, "ICT");
		tblDepartment department_obj_two = service.createtblDepartment(53, "ECE");
		tblDepartment department_obj_three = service.createtblDepartment(13, "AME");
		department_repo.save(department_obj_one);
		department_repo.save(department_obj_two);
		department_repo.save(department_obj_three);
		// 9. TBL FACULTY
		tblFaculty faculty_obj_one = service.createtblFaculty("Information", "Communication", "and", department_obj_one);
		tblFaculty faculty_obj_two = service.createtblFaculty("Computer", "Engineering", "and", department_obj_one);
		tblFaculty faculty_obj_three = service.createtblFaculty("Electrical", "Power", " ", department_obj_two);
		tblFaculty faculty_obj_four = service.createtblFaculty("Electrical", "Communication", "Digital", department_obj_two);
		tblFaculty faculty_obj_five = service.createtblFaculty("Advanced", "Material", "Engineering", department_obj_three);
		faculty_repo.save(faculty_obj_one);
		faculty_repo.save(faculty_obj_two);
		faculty_repo.save(faculty_obj_three);
		faculty_repo.save(faculty_obj_four);
		faculty_repo.save(faculty_obj_five);
		//  10. TBL FACULTY USER
		tblFacultyUser facultyuser_one = service.createFacultyUser("hninayethant@gmail.com", "1234",faculty_obj_one);
		tblFacultyUser facultyuser_two = service.createFacultyUser("thirihaymarkyaw@gmail.com", "1234",faculty_obj_one);
		tblFacultyUser facultyuser_three = service.createFacultyUser("phyuphyuthar@gmail.com", "1234",faculty_obj_one);
		tblFacultyUser facultyuser_four = service.createFacultyUser("htinkyawoo@gmail.com", "1234",faculty_obj_three);
		tblFacultyUser facultyuser_five = service.createFacultyUser("nanthinzarmyint@gmail.com", "1234",faculty_obj_three);
		tblFacultyUser facultyuser_six = service.createFacultyUser("thihazaw@gmail.com", "1234",faculty_obj_three);
		tblFacultyUser facultyuser_seven = service.createFacultyUser("soelinhtike@gmail.com", "1234", faculty_obj_five);
		facultyuser_repo.save(facultyuser_one);
		facultyuser_repo.save(facultyuser_two);
		facultyuser_repo.save(facultyuser_three);
		facultyuser_repo.save(facultyuser_four);
		facultyuser_repo.save(facultyuser_five);
		facultyuser_repo.save(facultyuser_six);
		facultyuser_repo.save(facultyuser_seven);
		// 11. TBL COURSE
		*/
		/*
		 * tblCourses course_obj_one = service.createtblCourses("013", "BIGDATA", "46",
		 * department_obj_one); tblCourses course_obj_two =
		 * service.createtblCourses("085", "NLP", "25", department_obj_one); tblCourses
		 * course_obj_three = service.createtblCourses("053", "BitCoin", "81",
		 * department_obj_one); tblCourses course_obj_four =
		 * service.createtblCourses("031", "Assembly", "5", department_obj_two);
		 * tblCourses course_obj_five = service.createtblCourses("565", "MATLAB", "24",
		 * department_obj_two); tblCourses course_obj_six =
		 * service.createtblCourses("4", "C", "15", department_obj_two); tblCourses
		 * course_obj_seven = service.createtblCourses("35", "Chemistry", "54",
		 * department_obj_three); tblCourses course_obj_eight =
		 * service.createtblCourses("13", "Nano", "24", department_obj_three);
		 * tblCourses course_obj_nine = service.createtblCourses("6", "Micro", "98",
		 * department_obj_three); course_repo.save(course_obj_one);
		 * course_repo.save(course_obj_two); course_repo.save(course_obj_three);
		 * course_repo.save(course_obj_four); course_repo.save(course_obj_five);
		 * course_repo.save(course_obj_six); course_repo.save(course_obj_seven);
		 * course_repo.save(course_obj_eight); course_repo.save(course_obj_nine);
		 
		// 12. TBL STUDENT GRADES
		tblStudentGrades studentgrades_obj_one = service.createtblStudentGrades("A", 2, 2, 5, course_obj_one, student_obj_three);
		tblStudentGrades studentgrades_obj_two = service.createtblStudentGrades("A", 5, 1, 5, course_obj_two, student_obj_two);
		tblStudentGrades studentgrades_obj_three = service.createtblStudentGrades("B", 4, 1, 5, course_obj_three, student_obj_one);
		tblStudentGrades studentgrades_obj_four = service.createtblStudentGrades("B", 5, 1, 5, course_obj_two, student_obj_one);
		tblStudentGrades studentgrades_obj_five = service.createtblStudentGrades("C", 4, 1, 5, course_obj_one, student_obj_one);
		grade_repo.save(studentgrades_obj_one);
		grade_repo.save(studentgrades_obj_two);
		grade_repo.save(studentgrades_obj_three);
		grade_repo.save(studentgrades_obj_four);
		grade_repo.save(studentgrades_obj_five);
		*/
		/*
		// 13. TBL APPLICANT USER
		tblApplicantUser applicantuser_obj_one = service.createtblApplicantUser("Kyaw", "NanDa", "male", "2/6/1996", "Yangon", "095748374", "kyawnanda1994@gmail.com");
		tblApplicantUser applicantuser_obj_two = service.createtblApplicantUser("Moo", "Ler", "male", "8/4/1998", "Mandalay", "095874732", "mooLer1998@gmail.com");
		tblApplicantUser applicantuser_obj_three = service.createtblApplicantUser("Esther", "", "female", "1/6/1997", "ChinState", "095984728", "esther1995@gmail.com");
		applicantuser_repo.save(applicantuser_obj_one);
		applicantuser_repo.save(applicantuser_obj_two);
		applicantuser_repo.save(applicantuser_obj_three);
		 
		System.err.println("\t \t \t Congratulation! Complete Data Initializing! \t \t \t");
		*/
		return null;
	}
}