package com.jdc.students.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jdc.students.entity.Department;
import com.jdc.students.entity.Student;
import com.jdc.students.entity.Teacher;

@Stateless
@LocalBean
public class DepartmentService {

	@PersistenceContext
	private EntityManager em;
	public List<Department> getAll() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Department.getAll", Department.class).getResultList();
	}
	public void save(Department dept) {
		// TODO Auto-generated method stub
		if(dept.getId()==0) {
			em.persist(dept);
		}else {
			em.merge(dept);
		}
		System.out.println("Total time for saving partially"+System.nanoTime());
		
	}
	public Department findById(String stid) {
		// TODO Auto-generated method stub
		System.out.println("Total time for retrieving data by id"+System.nanoTime());
		return em.find(Department.class, Integer.parseInt(stid));
	}
	public void saveAll(List<String> stList) {
		// TODO Auto-generated method stub
		for(String line:stList) {
			System.out.println("Words from service "+line);
			Department s=getStudentFromLine(line);
			em.persist(s);
		}
		System.out.println("Total time for saving all data "+System.nanoTime());
	}
	private Department getStudentFromLine(String line) {
		// TODO Auto-generated method stub
		Department c=new Department();
		String[] stLine=line.split(",,",-2);
		System.out.println("Total length and value "+stLine.length+stLine.toString());

		for (String s : stLine) {
			
			System.out.println(s);
			
		}
		c.setDeptName(stLine[0]);
		c.setDeptPhone(stLine[1]);
		c.setOfficeMail(stLine[2]);
		
		return c;
	}

}
