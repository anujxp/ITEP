package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import util.HibernateUtil;
import util.JPAUtil;
import util.PasswordHashUtil;

public class UserDAO {
	public static User findById(int userId) {
		User user = null;
		EntityManagerFactory sessionFactory = JPAUtil.getFactory()
		try (EntityManager session = sessionFactory.createEntityManager()) {
			user = session.find(User.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static boolean authenticate(User user) {
		SessionFactory sessionFactory = HibernateUtil.getFactory();
		try(Session session = sessionFactory.openSession();){
				TypedQuery<User> query = session.createQuery("from User where email = :email", User.class);
				 query.setParameter("email", user.getEmail());
				 User dbUser =  query.getSingleResult();
				 if(dbUser == null)
			    	 return false;
			     return PasswordHashUtil.checkPassword(user.getPassword(), dbUser.getPassword());  
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException (e);
			
		}
	}
	public static boolean save(User user) {
		  SessionFactory sessionFactory = HibernateUtil.getFactory();
		  Transaction transaction = null;
		  try (Session session = sessionFactory.openSession();){
			  transaction = session.beginTransaction();
			  session.persist(user);
			  transaction.commit();
			  return true;
		  }
		  catch(Exception e) {
			  e.printStackTrace();
			  if(transaction != null && transaction.isActive())
				  transaction.rollback();
		  }
		  return false;
	  }
	
}

