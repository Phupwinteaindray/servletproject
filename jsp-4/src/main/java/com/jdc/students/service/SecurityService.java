package com.jdc.students.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.students.entity.Account;
import com.jdc.students.exception.StudentRegistrationException;

@Stateless
@LocalBean
public class SecurityService {

	@PersistenceContext
	private EntityManager em;

	public Account login(String lgid, String password) {
		// TODO Auto-generated method stub
		Account account=null;
		List<Account> acc=em.createNamedQuery("Account.findbyloginid",Account.class).setParameter("lgid", lgid).getResultList();
		
		if(!acc.isEmpty()) {
			account=acc.get(0);
		}
		if(null==account) {
			throw new StudentRegistrationException(" Please check your account id and password");
		}
		if(!account.getPassword().equals(password)) {
			throw new StudentRegistrationException(" Please check your password");
		}
		return account;
	}
}
