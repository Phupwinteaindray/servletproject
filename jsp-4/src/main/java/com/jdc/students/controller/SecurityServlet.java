package com.jdc.students.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdc.students.entity.Account;
import com.jdc.students.service.SecurityService;

@WebServlet({ "/login", "/logout" })
public class SecurityServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB
	private SecurityService service;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=req.getSession(true);
		String path=req.getServletPath();
		if("/login".equals(path)) {
			try {
				String lgid=req.getParameter("loginid");
				String password=req.getParameter("pass");
				//Check already login
				Object result=getServletContext().getAttribute(lgid);
				if(null!=result) {
					throw new RuntimeException("Already Log in with another browser");
				}
				Account login=service.login(lgid,password);
				
				session.setAttribute("login", login);
			}catch(Exception e) {
				
				req.setAttribute("message", null == e.getCause() ? e.getMessage() : e.getCause().getMessage());
				getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
				return;
			}
			
		}else if("/logout".equals(path)) {
			session.invalidate();
		}
		resp.sendRedirect(req.getContextPath().concat("/home"));
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}
}
