package dao;

import java.util.Scanner;

import entity.Book;
import entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import util.JpaUtil;

public class CategoryDao {
		
	
	
		 public static int createCategoryWithBooks() {
			 Scanner sc = new Scanner(System.in);
			 EntityManagerFactory emf = JpaUtil.getFactory();
			
			 try(EntityManager em = emf.createEntityManager()){
				 EntityTransaction transaction = em.getTransaction();
		        transaction.begin();
		        Category cat = new Category();
		        System.out.println("Enter Category name....");
		        cat.setName(sc.next());
		        System.out.println("Enter category detaiil");
		        cat.setDescription(sc.next());
		        
				System.out.println("how many book you want to add (min 3");
				int n = sc.nextInt();
				for(int i = 0; i<n;i++) {
					Book b1 = new Book();
					System.out.println("Entr book"+i+1+"Title");
					b1.setTitle(sc.next());
					System.out.println("Enter book author");
					b1.setAuthor(sc.next());
					System.out.println("Enter price");
					b1.setPrice(sc.nextDouble());
					em.persist(b1);				
					b1.setCategory(cat);
					cat.addBook(b1);
				}
		       
		        em.persist(cat); 
		        transaction.commit();
		        
		        System.out.println("Created Category: " + cat.getName());
		        return cat.getId();
		    }catch(Exception e) {
		    	e.printStackTrace();
		 }
			 return 0;
		 }
		 
		 public static void deleteCategoryCascading() {
			 Scanner sc = new Scanner(System.in);
			 EntityManagerFactory emf = JpaUtil.getFactory();
			
			 try(EntityManager em = emf.createEntityManager()){
				 EntityTransaction transaction = em.getTransaction();
		        transaction.begin();
		        int categoryId = sc.nextInt();
		        
		        Category cat = em.find(Category.class, categoryId);
		        if (cat != null) {
		            System.out.println("Deleting Category: " + cat.getName());
		            em.remove(cat); 
		        }
		        em.getTransaction().commit();
			 }
		    }
		 public static void listBooksByCategory() {
			 System.out.println("Enter category id ");
			 Scanner sc = new Scanner(System.in);
			 int categoryId = sc.nextInt();
			 EntityManagerFactory emf = JpaUtil.getFactory();
			 
			
			 try(EntityManager em = emf.createEntityManager()){
//				 EntityTransaction transaction = em.getTransaction();
//		        transaction.begin();
		        
		        Category cat = em.find(Category.class, categoryId);
		        if (cat != null) {
		            System.out.println("Category: " + cat.getName());
		            for (Book b : cat.getBooks()) {
		                System.out.println(  b.getTitle() + " ($" + b.getPrice() + ")");
		            }
		        }
		    }

	}
}
