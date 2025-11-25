package com.info.CRUD;
import java.util.Scanner;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Passport;
import entity.User;
import util.HibernateUtil;
import util.Crud;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    int choice;
    Scanner sc = new Scanner(System.in);
    	while(true)
    {
    	System.out.println("Enter 1 for inserting the data");
    	System.out.println("Enter 0 for Exit");
    	choice = sc.nextInt();
    	switch(choice ) {
    	case 1 : Crud.insert();
    	case 2 : Crud.update();
    	case 3 : Crud.fetchaById();
    	
    	case 0 : System.exit(0);
    	}
    	
    	
    }
    }
    
}
