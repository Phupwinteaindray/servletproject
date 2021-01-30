package com.jdc.students.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.students.entity.Course;
import com.jdc.students.entity.Course.Level;



@Stateless
@LocalBean
public class CourseService {
	@PersistenceContext
	private EntityManager em;
	
	public Course save(Course c) {
		if(c.getId()==0) {
			em.persist(c);
		}else {
			em.merge(c);
		}
		return null;
	}

	public List<Course> getAll() {
		// TODO Auto-generated method stub
		System.out.println("Total time for retrieving all data "+System.nanoTime());
		return em.createNamedQuery("Course.getAll",Course.class).getResultList();
	}

	public Course findById(int id) {
		// TODO Auto-generated method stub
		System.out.println("Total time for retrieving data by id"+System.nanoTime());
		return em.find(Course.class, id);
	}

	public void saveAll(List<String> stList) {
		// TODO Auto-generated method stub
		
		for(String line:stList) {
			
			Course p=getCourseFromLine(line);
			em.persist(p);
		}
		System.out.println("Total time for saving all data "+System.nanoTime());
		
	}

	private Course getCourseFromLine(String line) {
		// TODO Auto-generated method stub
		Course c=new Course();
		String[] stLine=line.split(",");
		for (String s : stLine) {
			System.out.println(s);
		}
		c.setName(stLine[0]);
		c.setLevel(Level.valueOf(stLine[1]));
		c.setFees(Integer.valueOf(stLine[2]));
		c.setHours(Integer.valueOf(stLine[3]));
		c.setDescription(stLine[4]);
		
		return c;
	}



	
}
