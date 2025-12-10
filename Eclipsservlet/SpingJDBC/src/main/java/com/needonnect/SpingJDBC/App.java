package com.needonnect.SpingJDBC;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;
import com.needonnect.SpingJDBC.dao.StudentDAO;
import com.needonnect.SpingJDBC.entity.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        while(true) {
        System.out.println("Press 1 for insert");
        System.out.println("Press 2 for Update");
        System.out.println("Press 3 for delete");
        System.out.println("Press 0 for exsit");
        int choice = sc.nextInt();
        
        if (choice == 0)
			System.exit(0);
		else if (choice == 1)
			insert();
		else if (choice == 2)
			updateStudent();
		else if (choice == 3)
			deleteStudent();
        
    }
    }

	private static void deleteStudent() {
		try {
		   Scanner sc = new Scanner(System.in);
		   System.out.println("Enter the id you want to delete");
		   int id = sc.nextInt();
		   Student s = new Student();
		   ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
			StudentDAO dao = context.getBean("getStudentDAO", StudentDAO.class);
			if (dao.delete(id))
				System.out.println("Record Saved");
			else
				System.out.println("Somethig wrong...");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void updateStudent() {
		try 
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the name of the Student");
			String name = sc.next();
			System.out.println("Enter the course of the student");
			String course = sc.next();
			
			Student s = new Student();
			s.setName(name);
			s.setCourse(course);
			ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
			StudentDAO dao = context.getBean("getStudentDAO", StudentDAO.class);
			if (dao.update(s))
				System.out.println("Record Saved");
			else
				System.out.println("Somethig wrong...");

			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	private static void insert() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the name of the Student");
			String name = sc.next();
			System.out.println("Enter the course of the student");
			String course = sc.next();
			
			Student s = new Student();
			s.setName(name);
			s.setCourse(course);
			
			ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
			StudentDAO dao = context.getBean("getStudentDAO", StudentDAO.class);
			if (dao.save(s))
				System.out.println("Record Saved");
			else
				System.out.println("Somethig wrong...");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
