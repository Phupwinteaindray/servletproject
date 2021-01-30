package com.jdc.students.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.jdc.students.entity.Account;


@WebListener
public class LogOutListener implements HttpSessionListener {

    
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	HttpSession session=se.getSession();
    	Account acc=(Account) session.getAttribute("login");
    	if(null!=acc) {
    		session.getServletContext().removeAttribute(acc.getLogInId());
    	}
    }
	
}
