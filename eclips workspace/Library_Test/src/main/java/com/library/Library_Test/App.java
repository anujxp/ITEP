package com.library.Library_Test;

import java.util.Scanner;

import dao.BookDao;
import dao.CategoryDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import util.JpaUtil;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		 
		 
		Scanner sc = new Scanner(System.in);
		
		System.out.println("--------------------Library----------------");
		System.out.println("1: Create Category with 3 Books ");
		System.out.println("2: List Books in Category");
		System.out.println("3: Delete Book ) ");
		System.out.println("4: Update Book Price");
		System.out.println("5: Delete Category ");
		int choice = sc.nextInt();
		
		switch(choice) {
        case 1: CategoryDao.createCategoryWithBooks();break;
        case 2: CategoryDao.listBooksByCategory();break;
        case 3: BookDao.deleteBookOnly();break;
        case 5: CategoryDao.deleteCategoryCascading(); break;
        case 4: BookDao.updateBookPrice();break;
        case 0: System.exit(choice);
        }
		
	}
}
