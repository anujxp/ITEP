package dao;

import java.util.Scanner;

import entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import util.JpaUtil;

public class BookDao {
	public static void deleteBookOnly() {
		Scanner sc = new Scanner(System.in);
		 EntityManagerFactory emf = JpaUtil.getFactory();
		
		 try(EntityManager em = emf.createEntityManager()){
			 EntityTransaction transaction = em.getTransaction();
	        transaction.begin();
//        em.getTransaction().begin();
        System.out.println("Enter book id ");
        int bookId = sc.nextInt();
        Book book = em.find(Book.class, bookId);
        if (book != null) {
            System.out.println("Deleting Book: " + book.getTitle());
            book.getCategory().getBooks().remove(book); // Maintain object consistency
            em.remove(book);
        }
        em.getTransaction().commit();
    }catch(Exception e ) {
    	e.printStackTrace();
    }
}
}
