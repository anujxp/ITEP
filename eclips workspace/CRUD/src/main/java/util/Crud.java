package util;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Passport;
import entity.User;

public class Crud {
public static void insert() {
	
	SessionFactory sessionFactory = HibernateUtil.getFactory();
	Scanner sc = new Scanner(System.in);
	Transaction transaction = null;
	try(Session session = sessionFactory.openSession();){
		User user = new User();
		Passport passport = new Passport();
		System.out.println("Enter user name");
		user.setName(sc.nextLine());
		System.out.println("Enter user Address");
		user.setAddress(sc.nextLine());
		System.out.println("Enter passport id");
		while (!sc.hasNextInt()) {
            System.out.println("That's not a number! Please enter a valid Passport ID:");
            sc.next(); 
        }
		passport.setPassId(sc.nextInt());
		sc.nextLine();
		passport.setUser(user);
		user.setPassport(passport);
		
		
		transaction = session.beginTransaction();
		
		session.persist(user);
		transaction.commit();
		System.out.println("Inserted successfully");
	}catch(Exception e) {
		e.printStackTrace();
		if(transaction != null) {
			transaction.rollback();
			System.err.println("Error while Inserting the data");
		}
	}
}

public static void update() {
	SessionFactory sessionFactory = HibernateUtil.getFactory();
	Scanner sc = new Scanner(System.in);
	Transaction transaction  = null;
	try(Session session =  sessionFactory.openSession()){
		User user = new User();
		Passport passport = new Passport();
		
		System.out.println("Enter user id to Update ");
		int id = sc.nextInt();
		
	}
}

public static void fetchaById() {
	SessionFactory sessionFactory = HibernateUtil.getFactory();
	Scanner sc = new Scanner(System.in);
	try(Session session = sessionFactory.openSession()){
		User user = new User();
		
	}
			
	
}
}
