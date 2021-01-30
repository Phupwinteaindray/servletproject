package com.jdc.students.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.jdc.students.entity.Department;
import com.jdc.students.entity.Student;
import com.jdc.students.entity.Teacher;

@Stateless
@LocalBean
public class TeacherService {

	@PersistenceContext
	private EntityManager em;
	public List<Teacher> search(String name, String department) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer("select s from Teacher s where 1 = 1");
		Map<String, Object> params = new HashMap<>();
		
		if(!StudentUtils.isEmpty(name)) {
			sb.append(" and lower(s.name) like lower(:name)");
			params.put("name", name.concat("%"));
		}
		
		if(!StudentUtils.isEmpty(department)) {
			sb.append(" and lower(s.department.name) like lower(:department)");
			params.put("department", department.concat("%"));
		}
		
		TypedQuery<Teacher> query = em.createQuery(sb.toString(), Teacher.class);
		
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		System.out.println("Total time for selecting some data "+System.nanoTime());
		return query.getResultList();
	}
	public Teacher findById(String stid) {
		// TODO Auto-generated method stub
		System.out.println("Total time for retrieving data by id"+System.nanoTime());
		return em.find(Teacher.class, Integer.parseInt(stid));
	}
	public void deleteById(String stid) {
		// TODO Auto-generated method stub
		Teacher s=em.find(Teacher.class, Integer.parseInt(stid));
		em.remove(s);
		System.out.println("Total time for deleting data by id"+System.nanoTime());
	}
	public Object getAll() {
		// TODO Auto-generated method stub
		System.out.println("Total time for retrieving all data"+System.nanoTime());
		return em.createNamedQuery("Teacher.getAll",Teacher.class).getResultList();
	}
	public void create(String id,String name,String phone,String email, String dept) {
		// TODO Auto-generated method stub
		Department tchel = em.find(Department.class, Integer.parseInt(dept));
		Teacher teacher=new Teacher();
		teacher.setId((null != id && !id.isEmpty()) ? Integer.parseInt(id) : 0);
		teacher.setName(name);
		teacher.setPosition(phone);
		teacher.setDepartment(tchel);
		teacher.setTeacherEmail(email);
		if(teacher.getId()==0) {
			em.persist(teacher);
		}else {
			em.merge(teacher);
		}
		
		
	}
	public void saveAll(List<String> stList) {
		// TODO Auto-generated method stub
		for(String line:stList) {
			System.out.println("Words from service "+line);
			Teacher s=getStudentFromLine(line);
			em.persist(s);
		}
		System.out.println("Total time for saving all data "+System.nanoTime());
	}
	private Teacher getStudentFromLine(String line) {
		// TODO Auto-generated method stub
		Teacher c=new Teacher();
		String[] stLine=line.split(",,",-2);
		System.out.println("Total length and value "+stLine.length+stLine.toString());

		for (String s : stLine) {
			
			System.out.println(s);
			
		}
		
		c.setName(stLine[0]);
		c.setPosition(stLine[1]);
		c.setTeacherEmail(stLine[2]);
		Department dept = findCategoryByName(stLine[3]);
		c.setDepartment(dept);
		
		return c;
	}
	private Department findCategoryByName(String name) {
		// TODO Auto-generated method stub
		TypedQuery<Department> query = em.createNamedQuery("Department.findByName", Department.class);
		query.setParameter("name", name);
		return query.getSingleResult();
	}

}
