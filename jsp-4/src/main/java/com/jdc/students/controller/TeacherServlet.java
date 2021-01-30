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

import com.jdc.students.entity.Student;
import com.jdc.students.entity.Teacher;
import com.jdc.students.service.DepartmentService;
import com.jdc.students.service.TeacherService;
@WebServlet({ "/auth/teacher", "/auth/teacher-edit","/auth/teacher-upload","/auth/teacher-delete" })
@MultipartConfig
public class TeacherServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@EJB
	private DepartmentService department;
	@EJB
	private TeacherService service;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path=req.getServletPath();
		String view=null;
		if("/auth/teacher".equals(path)) {
			String name=req.getParameter("name");
			String department=req.getParameter("department");			
			List<Teacher> tchelList=service.search(name,department);
			req.setAttribute("list", tchelList);
			view="/views/teacher-list.jsp";
		}else if("/auth/teacher-edit".equals(path)){
			String Stid=req.getParameter("id");
			if(null!=Stid && !Stid.isEmpty()) {
				Teacher std=service.findById(Stid);
				req.setAttribute("student", std);
				
			}
			view="/views/teacher-edit.jsp";
		}else if("/auth/teacher-delete".equals(path)) {
			String stid=req.getParameter("id");
			if(null!=stid && !stid.isEmpty()) {
				service.deleteById(stid);
				req.setAttribute("list", service.getAll());
			}
			view="/views/teacher-list.jsp";
		}
		req.setAttribute("courses", department.getAll());
		getServletContext().getRequestDispatcher(view).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(req.getServletPath().equals("/auth/teacher-edit")) {
			String id=req.getParameter("id");
			String name=req.getParameter("name");
			String dept=req.getParameter("department");
			String phone=req.getParameter("position");
			String email=req.getParameter("email");
			service.create(id,name,phone,email,dept);
		}else if("/auth/teacher-upload".equals(req.getServletPath())) {
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
		resp.sendRedirect(req.getContextPath().concat("/auth/teacher"));

	}
}
