package com.example.SpringDemo.Model;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.SpringDemo.Model.Student.Address;
import com.example.SpringDemo.Model.Student.Student;

public class StudentModel 
{
	public List<Student> getAll()
	{
		List<Student> list = null;
		
		Session s = this.getSession();
		Transaction t = s.beginTransaction();
		Query query = s.createQuery("from Student");
		list = (List<Student>)query.list();
		t.commit();
		s.close();

		return list;
	}
	
	public Student get(int id)
	{
		Student stu = null;
		Session s = this.getSession();
		stu = s.get(Student.class , id);
		s.close();
		return stu;
	}
	
	public Session getSession()
	{
		Configuration conf = new Configuration();
//		conf.configure("application.properties").addAnnotatedClass(Student.class).addAnnotatedClass(Address.class);
		conf.addAnnotatedClass(Student.class).addAnnotatedClass(Address.class);
		SessionFactory sf = conf.buildSessionFactory();
		Session s = sf.openSession();
		return s;
	}
	
	public int save(HashMap<String , String> hmap)
	{
		Student stu = new Student();
		Session s = this.getSession();
		Transaction t = s.beginTransaction();
		if(hmap.get("studentId") != "")
		{
			stu = s.get(Student.class, Integer.parseInt(hmap.get("studentId")));
		}
		stu.setName(hmap.get("name"));
		stu.setAge(Integer.parseInt(hmap.get("age")));
		stu.setBirthDate(hmap.get("birthDate"));
		s.saveOrUpdate(stu);
		t.commit();
		s.close();
		return stu.getStudentId();
	}
	
	public void delete(int id)
	{
		Student stu = new Student();
		Session s = this.getSession();
		Transaction t = s.beginTransaction();
		s.delete(s.get(Student.class , id));
		t.commit();
		s.close();
	}
}
