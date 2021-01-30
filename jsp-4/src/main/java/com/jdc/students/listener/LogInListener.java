package com.jdc.students.listener;

import javax.servlet.annotation.WebListener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.jdc.students.entity.Account;
@WebListener
public class LogInListener implements HttpSessionAttributeListener{
@Override
public void attributeAdded(HttpSessionBindingEvent event) {
	// TODO Auto-generated method stub
	
	if(event.getName().equals("login")) {
		Account account=(Account) event.getValue();
		if(null!=account) {
			event.getSession().getServletContext().setAttribute(account.getLogInId(), account);
		}
		
	}
}
}
