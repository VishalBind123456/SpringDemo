package com.example.SpringDemo.Controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.SpringDemo.Model.StudentModel;
import com.example.SpringDemo.Model.Student.Student;

@Controller
public class StudentController 
{
	@RequestMapping(value="/grid")
	public ModelAndView  grid()
	{
		System.out.println("inside grid");
		StudentModel studentModel = new StudentModel();
		List<Student> students = studentModel.getAll();
		for(Student stu : students)
		{
			System.out.println(stu);
		}
		
		ModelAndView gridView = new ModelAndView("/view/student/grid");
		gridView.addObject("students" , students);
		return gridView;
	}
	
	@RequestMapping(value="/grid2")
	@ResponseBody
	public Map<String , Object>  grid2()
	{
		StudentModel studentModel = new StudentModel();
		List<Student> students = studentModel.getAll();
		
		String path = "src/main/webapp/view/student/grid2.html";
		String htmlString = this.getTemplateString(path, students);
		
		Map<String , Object> map = new HashMap<>();
		map.put("grid2", htmlString);
		return map;
	}
	
	@RequestMapping(value="/upsert")
	public ModelAndView upsert(HttpServletRequest req)
	{
		System.out.println("inside upsert");

		StudentModel studentModel = new StudentModel();
		Student stu = new Student();
		if(req.getParameterMap().containsKey("studentId"))
		{
			int id = Integer.parseInt(req.getParameter("studentId"));
			stu = studentModel.get(id);
		}
		ModelAndView upsertView = new ModelAndView("/view/student/upsert");
		upsertView.addObject("student" , stu);
		return upsertView;
	}
	
	@RequestMapping(value="/upsert2")
	@ResponseBody
	public Map<String , Object> upsert2(HttpServletRequest req)
	{
		
		StudentModel studentModel = new StudentModel();
		Student stu = new Student();
		if(req.getParameterMap().containsKey("studentId"))
		{
			int id = Integer.parseInt(req.getParameter("studentId"));
			stu = studentModel.get(id);
		}
		String path = "src/main/webapp/view/student/upsert2.html";
		String htmlString = this.getTemplateString(path, stu);
		
		Map<String , Object> map = new HashMap<>();
		map.put("upsert2", htmlString);
		return map;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String , Object> save(HttpServletRequest req)
	{
		System.out.println("inside save");
		HashMap<String , String> hmap = new HashMap<String , String>();
		hmap.put("studentId" , req.getParameter("studentId"));
		hmap.put("name" , req.getParameter("name"));
		hmap.put("age" , req.getParameter("age"));
		hmap.put("birthDate" , req.getParameter("birthDate"));
		int id = new StudentModel().save(hmap);
		System.out.println(id + " <--- saved");
		return this.grid2();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String , Object> delete(HttpServletRequest req)
	{
		System.out.println("inside delete");
		int id = Integer.parseInt(req.getParameter("studentId"));
		new StudentModel().delete(id);
		System.out.println(id + " <--- deleted");
		return this.grid2();
	}
	
	public String getTemplateString(String filePath , Object data)
	{
		String htmlCont = null;
		try 
		{
			Document doc = Jsoup.parse(new File(filePath) , "utf-8");
			String strDoc = doc.toString(); 
			TemplateEngine tempEng = new TemplateEngine();
			Context ctx = new Context();
			ctx.setVariable("data", data);
			htmlCont = tempEng.process(strDoc , ctx);
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return htmlCont;
	}
		
}
