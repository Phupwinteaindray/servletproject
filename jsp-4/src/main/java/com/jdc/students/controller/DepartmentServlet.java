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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.jdc.students.entity.Account;
import com.jdc.students.entity.Department;
import com.jdc.students.entity.Registration;
import com.jdc.students.entity.Student;
import com.jdc.students.service.DepartmentService;
@WebServlet({ "/home", "/auth/department-edit","/auth/department-upload" })
@MultipartConfig
public class DepartmentServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@EJB
	private DepartmentService service;
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
				//add to request scope
				req.setAttribute("list", service.getAll());
				view="/views/registration.jsp";
			}
		}else if("/auth/department-edit".equals(path)) {
			String Stid=req.getParameter("id");
			if(null!=Stid && !Stid.isEmpty()) {
				Department std=service.findById(Stid);
				req.setAttribute("student", std);
				
			}
			view="/views/registration-edit.jsp";
		}
		getServletContext().getRequestDispatcher(view).forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if("/auth/department-edit".equals(req.getServletPath())) {
			String id=req.getParameter("id");
			String course=req.getParameter("deptName");
			String applyDate=req.getParameter("officeMail");
			String name=req.getParameter("deptPhone");
			Department dept=new Department();
			dept.setId((null != id && !id.isEmpty()) ? Integer.parseInt(id) : 0);
			dept.setDeptName(course);
			dept.setOfficeMail(applyDate);
			dept.setDeptPhone(name);
			service.save(dept);
		}else if("/auth/department-upload".equals(req.getServletPath())) {
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
		resp.sendRedirect(req.getContextPath().concat("/home"));
	}
}
