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
import com.jdc.students.entity.Course.Level;
import com.jdc.students.service.CourseService;

@WebServlet({ "/auth/courses", "/auth/courses-edit","/auth/courses/courses-upload" })
@MultipartConfig
public class CourseServlet extends HttpServlet {
	@EJB
	private CourseService service;
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath();
		String view = null;
		if ("/auth/courses".equals(path)) {
			// course list

			List<Course> courseList = service.getAll();
			req.setAttribute("list", courseList);
			view = "/views/course-list.jsp";
	
			
			
			
		} else {
			String str = req.getParameter("id");

			if (null != str && !str.isEmpty()) {
				int id = Integer.parseInt(str);
				Course course = service.findById(id);
				req.setAttribute("course", course);
			}

			req.setAttribute("level", Level.values());

			view = "/views/course-edit.jsp";
		}
		System.out.println("Current path from doGet()"+req.getServletPath());
		getServletContext().getRequestDispatcher(view).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(req.getServletPath().equals("/auth/courses-edit")) {
		// Save course
			String name = req.getParameter("name");
			String id = req.getParameter("id");
			String level = req.getParameter("level");
			String hour = req.getParameter("hour");
			String fees = req.getParameter("fees");
			String description = req.getParameter("descripton");
			Course c = new Course();
			c.setId((null != id && !id.isEmpty()) ? Integer.parseInt(id) : 0);
			c.setName(name);
			c.setDescription(description);
			c.setFees(Integer.parseInt(fees));
			c.setHours(Integer.parseInt(hour));
			c.setLevel(com.jdc.students.entity.Course.Level.valueOf(level));
			service.save(c);
		}else{
			Part part=req.getPart("file");
			try (BufferedReader buffer=new BufferedReader(new InputStreamReader(part.getInputStream()))){
				String line=null;
				List<String> stList=new ArrayList<String>();
				while(null != (line = buffer.readLine())) {
					stList.add(line);
					System.out.println(line);
				}
				service.saveAll(stList);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Current Path from doPost " +req.getServletPath());
		resp.sendRedirect(req.getContextPath().concat("/auth/courses"));
	}
}
