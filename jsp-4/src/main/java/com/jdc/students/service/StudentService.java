package com.jdc.students.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.jdc.students.entity.Student;


@Stateless
@LocalBean
public class StudentService {
	@PersistenceContext
	private EntityManager em;

	public List<Student> search(String name, String phone) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("select s from Student s where 1 = 1");
		Map<String, Object> params = new HashMap<>();
		
		if(!StudentUtils.isEmpty(name)) {
			sb.append(" and lower(s.name) like lower(:name)");
			params.put("name", name.concat("%"));
		}
		
		if(!StudentUtils.isEmpty(phone)) {
			sb.append(" and lower(s.email) like lower(:phone)");
			params.put("phone", phone.concat("%"));
		}
		
		TypedQuery<Student> query = em.createQuery(sb.toString(), Student.class);
		
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		System.out.println("Total time for selecting some data "+System.nanoTime());
		return query.getResultList();
	}

	public Student findById(String id) {
		// TODO Auto-generated method stub
		System.out.println("Total time for retrieving data by id"+System.nanoTime());
		return em.find(Student.class, Integer.parseInt(id));
	}

	public void update(String id, String name, String email, String phone) {
		// TODO Auto-generated method stub
		Student s=em.find(Student.class, Integer.parseInt(id));
		if(null!=s) {
			s.setName(name);
			s.setEmail(email);
			s.setPhone(phone);
		}
		System.out.println("Total time for updating data by id"+System.nanoTime());
	}

	public void save(Student c) {
		// TODO Auto-generated method stub
		if(c.getId()==0) {
			em.persist(c);
		}else {
			em.merge(c);
		}
		System.out.println("Total time for saving partially"+System.nanoTime());
		
	}

	public void saveAll(List<String> stList) {
		// TODO Auto-generated method stub
			for(String line:stList) {
				System.out.println("Words from service "+line);
				Student s=getStudentFromLine(line);
				em.persist(s);
			}
			System.out.println("Total time for saving all data "+System.nanoTime());
	}

	private Student getStudentFromLine(String line) {
		// TODO Auto-generated method stub
		Student c=new Student();
		String[] stLine=line.split(",,",-2);
		System.out.println("Total length and value "+stLine.length+stLine.toString());

		for (String s : stLine) {
			
			System.out.println(s);
			
		}
		c.setName(stLine[1]);
		c.setRollNumber(stLine[3]);
		c.setPhone(stLine[2]);
		c.setEmail(stLine[0]);
		
		return c;
	}

	public List<Student> getAll() {
		// TODO Auto-generated method stub
		System.out.println("Total time for retrieving all data"+System.nanoTime());
		return em.createNamedQuery("Student.getAll",Student.class).getResultList();
	}

	public void deleteById(String stid) {
		// TODO Auto-generated method stub
		Student s=em.find(Student.class, Integer.parseInt(stid));
		em.remove(s);
		System.out.println("Total time for deleting data by id"+System.nanoTime());
	}

	
}
