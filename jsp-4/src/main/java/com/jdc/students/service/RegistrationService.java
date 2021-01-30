package com.jdc.students.service;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.jdc.students.entity.Course;
import com.jdc.students.entity.Registration;
import com.jdc.students.entity.Student;

@Stateless
@LocalBean
public class RegistrationService {
	@PersistenceContext
	private EntityManager em;

	public void save(String course, String applyDate, Student s) {
		// TODO Auto-generated method stub
		Course c=em.find(Course.class, Integer.parseInt(course));
		Registration reg=new Registration();
		reg.setCourse(c);
		reg.setApplyDate(LocalDate.parse(applyDate,DateTimeFormatter.ISO_DATE));
		reg.setStudent(s);
		em.persist(reg);
	}

public List<Registration> search(String courseId, String student, String from, String to) {
		
		StringBuffer sb = new StringBuffer("select r from Registration r where 1 = 1");
		Map<String, Object> params = new  HashMap<>();
		
		if(!StudentUtils.isEmpty(courseId) && !"0".equals(courseId)) {
			sb.append(" and r.course.id = :course");
			params.put("course", Integer.parseInt(courseId));
		}
		
		if(!StudentUtils.isEmpty(student)) {
			sb.append(" and lower(r.student.name) like lower(:student)");
			params.put("student", student.concat("%"));
		}
		
		if(!StudentUtils.isEmpty(from)) {
			sb.append(" and r.applyDate >= :from");
			params.put("from", LocalDate.parse(from, DateTimeFormatter.ISO_DATE));
		}
		
		if(!StudentUtils.isEmpty(to)) {
			sb.append(" and r.applyDate <= :to");
			params.put("to", LocalDate.parse(to, DateTimeFormatter.ISO_DATE));
		}
		
		TypedQuery<Registration> query = em.createQuery(sb.toString(), Registration.class);
		
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}

		return query.getResultList();
	}
}
