package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Category;
import util.HibernateUtil;

public class CategoryDAO {
	public static Category findById(int id) {
		Category c = null;
		   SessionFactory sessionFactory = HibernateUtil.getFactory();
		   try (Session session = sessionFactory.openSession();){
			 c =  session.get(Category.class, id);
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
		   return c;
	}
	
	public static boolean save(Category category) {
		   SessionFactory sessionFactory = HibernateUtil.getFactory();
		   try (Session session = sessionFactory.openSession();){
			   Transaction transaction = session.beginTransaction();
			   session.persist(category);
			   transaction.commit();
			   return true;
		   }
		   catch(Exception e) {
			   e.printStackTrace();
		   }
		   return false;
	   }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
