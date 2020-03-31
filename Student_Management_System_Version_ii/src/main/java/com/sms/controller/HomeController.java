package com.sms.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sms.model.*;

@Controller
@RequestMapping("sms")
public class HomeController 
{
	@RequestMapping("/index")
	public String goHome(Model model)
	{
		tblAdminUser admin_user = new tblAdminUser();
		tblStudentUser student_user = new tblStudentUser();
		tblFacultyUser faculty_user = new tblFacultyUser();
		
		model.addAttribute("admin_user",admin_user);
		model.addAttribute("student_user",student_user);
		model.addAttribute("faculty_user",faculty_user);
		
		return "home/index";
	}
}
