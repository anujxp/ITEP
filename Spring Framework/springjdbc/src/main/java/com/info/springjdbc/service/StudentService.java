package com.info.springjdbc.service;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.info.springjdbc.JavaConfig;
import com.info.springjdbc.dao.StudentDAO;
import com.info.springjdbc.entity.Student;

public class StudentService {
	static Scanner se = new Scanner(System.in);
	
	public static void insert() {
		Student s = new Student();
		System.out.println("Enter name of student");
		s.setName(se.next());
		System.out.println("Enter course of student...");
		s.setCourse(se.next());
		ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
		StudentDAO dao = context.getBean("getStudentDAO",StudentDAO.class);
		if(dao.save(s))
			System.out.println("student saved....");
		else 
			System.out.println("something went wrong.....");
	}
	public static void update() {
		Student s = new Student();
		System.out.println("Enter id ");
		s.setId(se.nextInt());
		System.out.println("Enter name of student");
		s.setName(se.next());
		System.out.println("Enter course of student...");
		s.setCourse(se.next());
		ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
		StudentDAO dao = context.getBean("getStudentDAO",StudentDAO.class);
		if(dao.update(s))
			System.out.println("student update....");
		else 
			System.out.println("something went wrong.....");
	}
	
	public static void delete() {
		System.out.println("Enter Student id...");
		int id =  se.nextInt();
		ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
		StudentDAO dao = context.getBean("getStudentDAO",StudentDAO.class);
		if(dao.delete(id))
			System.out.println("Student deleted ....");
		else 
			System.out.println("something went wrong.....");
	}
	
	
}
