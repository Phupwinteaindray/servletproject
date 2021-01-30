package com.jdc.students.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.jdc.students.entity.Course;
import com.jdc.students.entity.Student;
import com.jdc.students.service.StudentService;

@WebServlet({ "/auth/students", "/auth/student-edit","/auth/student-upload","/auth/student-delete" })
@MultipartConfig
public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB
	private StudentService service;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path=req.getServletPath();
		String view=null;
		if("/auth/students".equals(path)) {
			String name=req.getParameter("name");
			String phone=req.getParameter("email");			
			List<Student> stdList=service.search(name,phone);
			req.setAttribute("list", stdList);
			view="/views/student-list.jsp";
		}else if("/auth/student-edit".equals(path)){
			String Stid=req.getParameter("id");
			if(null!=Stid && !Stid.isEmpty()) {
				Student std=service.findById(Stid);
				req.setAttribute("student", std);
				
			}
			view="/views/student-edit.jsp";
		}else if("/auth/student-delete".equals(path)) {
			String stid=req.getParameter("id");
			if(null!=stid && !stid.isEmpty()) {
				service.deleteById(stid);
				req.setAttribute("list", service.getAll());
			}
			view="/views/student-list.jsp";
		}
		System.out.println("Path from doGet()"+req.getServletPath());
		getServletContext().getRequestDispatcher(view).forward(req, resp);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(req.getServletPath().equals("/auth/student-edit")) {
			String id=req.getParameter("id");
			String name=req.getParameter("name");
			String email=req.getParameter("email");
			String phone=req.getParameter("phone");
			String rollNumber=req.getParameter("rollNumber");
			Student c = new Student();
			c.setId((null != id && !id.isEmpty()) ? Integer.parseInt(id) : 0);
			c.setName(name);
			c.setEmail(email);
			c.setPhone(phone);
			c.setRollNumber(rollNumber);
			service.save(c);
		}else if("/auth/student-upload".equals(req.getServletPath())){
			Part part=req.getPart("file");
			try (BufferedReader buffer=new BufferedReader(new InputStreamReader(part.getInputStream()))){
				String line=null;
				List<String> stList=new ArrayList<String>();
				while(null != (line = buffer.readLine())) {
					stList.add(line);
					System.out.println(line);
				}
				for(String st:stList) {
					System.err.println("Words from servlet "+st);
				}
				service.saveAll(stList);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Path from doPost()"+req.getServletPath());
		resp.sendRedirect(req.getContextPath().concat("/auth/students"));
		
	}
}
