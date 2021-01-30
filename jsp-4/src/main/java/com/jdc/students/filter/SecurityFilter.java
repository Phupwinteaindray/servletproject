package com.jdc.students.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jdc.students.entity.Account;

@WebFilter("/auth/*")
public class SecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// before target
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(true);
		Account acc = (Account) session.getAttribute("login");
		if (null == acc) {
			req.setAttribute("message", "Please log in again");
			req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, response);
		} else {
			chain.doFilter(request, response);
		}

	}

}
