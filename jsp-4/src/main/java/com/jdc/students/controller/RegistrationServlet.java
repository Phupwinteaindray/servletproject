package com.jdc.students.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdc.students.entity.Account;
import com.jdc.students.entity.Registration;
import com.jdc.students.entity.Student;
import com.jdc.students.service.CourseService;
import com.jdc.students.service.RegistrationService;

@WebServlet({ "/homme", "/auth/registration-edit" })
public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB
	private CourseService service;
	@EJB
	private RegistrationService registration;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path=req.getServletPath();
		String view=null;
		if("/home".equals(path)) {
			HttpSession session=req.getSession(true);
			Account loginUser=(Account) session.getAttribute("login");
			if(null==loginUser) {
				
				view="/index.jsp";
			}else {
				//search registration
				String courseId=req.getParameter("courses");
				String student=req.getParameter("student");
				String from=req.getParameter("from");
				String to=req.getParameter("to");
				List<Registration> regList=registration.search(courseId,student,from,to);
				
				//add to request scope
				req.setAttribute("list", regList);
				view="/views/registration.jsp";
			}
			
		}else if("/auth/registration-edit".equals(path)) {
			view="/views/registration-edit.jsp";
		}
		req.setAttribute("courses", service.getAll());
		getServletContext().getRequestDispatcher(view).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Save registration
		
		String course=req.getParameter("course");
		String applyDate=req.getParameter("applyDate");
		String name=req.getParameter("name");
		String email=req.getParameter("mail");
		String phone=req.getParameter("phone");
		
		Student s=new Student();
		
		s.setEmail(email);
		s.setPhone(phone);
		s.setName(name);
		
		System.out.println(course);
	
		registration.save(course, applyDate, s);
		resp.sendRedirect(req.getContextPath().concat("/home"));
	}
	
}
